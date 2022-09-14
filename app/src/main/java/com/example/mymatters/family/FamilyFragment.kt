package com.example.mymatters.family

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mymatters.R
import com.example.mymatters.mymattersdatabase.MyMattersDatabase
import com.example.mymatters.mymattersdatabase.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
class FamilyPersonViewPagerVH(itemView : View) : RecyclerView.ViewHolder(itemView){
    lateinit var image : ImageView
    lateinit var name : TextView
    lateinit var wallet : TextView
    lateinit var coinbox : TextView
    init {
        image = itemView.findViewById(R.id.photo_imageview)
        name = itemView.findViewById(R.id.name_textview)
        wallet = itemView.findViewById(R.id.wallet_textview)
        coinbox = itemView.findViewById(R.id.coinbox_textview)
    }
}

class FamilyPersonViewPagerAdapter : RecyclerView.Adapter<FamilyPersonViewPagerVH>(){
    private var __persons = listOf<Person>()
    var persons: List<Person>
        get() = __persons
        set(value) {
            __persons = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyPersonViewPagerVH = FamilyPersonViewPagerVH(
        LayoutInflater.from(parent.context).inflate(R.layout.family_card_item,parent,false)
    )

    override fun onBindViewHolder(holder: FamilyPersonViewPagerVH, position: Int) = holder.itemView.run{
        Toast.makeText(this.context,persons[position].photoUri,Toast.LENGTH_SHORT)
        if(persons[position].photoUri == "no_photo")holder.image.setImageResource(R.mipmap.ic_camera_foreground)
        else holder.image.setImageURI(Uri.parse(persons[position].photoUri))
        holder.name.text = persons[position].personName
        holder.wallet.text = persons[position].wallet.toString()
        holder.coinbox.text = persons[position].coinbox.toString()

    }

    override fun getItemCount(): Int {
        return persons.size
    }

}

class FamilyFragment : Fragment() {

    companion object {
        fun newInstance() = FamilyFragment()
    }

    private lateinit var viewModel: FamilyViewModel
    private lateinit var dialog : Dialog
    private var photoSelected : Uri? = null
    private lateinit var intent : ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent = registerForActivityResult(ActivityResultContracts.GetContent()){
            //imageView.setImageURI(it)
            photoSelected=it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_family, container, false)
        val fab = root.findViewById<FloatingActionButton>(R.id.fab_add_in_family)
        fab.setOnClickListener({
            val builder = AlertDialog.Builder(root.context)
            dialog = builder.setTitle(resources.getString(R.string.title_add_in_family))
                .setView(R.layout.family_add_person)
                .setPositiveButton(resources.getString(R.string.button_save),
                    { dialogInterface: DialogInterface, i: Int ->
                        val person = Person(-1,dialog.findViewById<EditText>(R.id.name_edittext).text.toString(),
                            if (photoSelected == null) "no_photo" else photoSelected!!.toString(),
                            dialog.findViewById<EditText>(R.id.wallet_editnumber).text.toString().toDouble(),
                            dialog.findViewById<EditText>(R.id.coinbox_editnumber).text.toString().toDouble())
                        Toast.makeText(root.context,"Oh my",Toast.LENGTH_SHORT)
                        viewModel.writePerson(person)
                    })
                .setNegativeButton(resources.getString(R.string.button_cancel),null)
                .create()
            dialog.show()
            val imageView = dialog.findViewById<ImageView>(R.id.input_photo_imageview)
            imageView.setOnClickListener({
                intent.launch("image/*")
            })
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FamilyViewModel::class.java)
        val personViewPager = view?.findViewById<ViewPager2>(R.id.person_viewpager)
        val personAdapter = FamilyPersonViewPagerAdapter()
        viewModel.persons.observe(viewLifecycleOwner, Observer {
            personAdapter.persons = it!!
        })
        personViewPager?.adapter = personAdapter
    }

}