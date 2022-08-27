package com.example.mymatters.family

import android.app.AlertDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymatters.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FamilyPersonViewPagerVH(itemView : View) : RecyclerView.ViewHolder(itemView)

class FamilyPersonViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = FamilyPersonViewPagerVH(
        LayoutInflater.from(parent.context).inflate(R.layout.family_card_item,parent,false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.itemView.run{

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class FamilyFragment : Fragment() {

    companion object {
        fun newInstance() = FamilyFragment()
    }

    private lateinit var viewModel: FamilyViewModel
    private lateinit var dialog : Dialog
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
                .setPositiveButton(resources.getString(R.string.button_save),null)
                .setNegativeButton(resources.getString(R.string.button_cancel),null)
                .create()
            dialog.show()
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FamilyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}