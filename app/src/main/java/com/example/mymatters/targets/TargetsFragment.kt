package com.example.mymatters.targets

import android.app.AlertDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymatters.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TargetRecyclerVH(itemView : View) : RecyclerView.ViewHolder(itemView)

/*class TargetRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var targets = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

}*/

class TargetsFragment : Fragment() {

    companion object {
        fun newInstance() = TargetsFragment()
    }

    private lateinit var viewModel: TargetsViewModel
    private lateinit var dialog : Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_targets, container, false)
        val fab = root.findViewById<FloatingActionButton>(R.id.fab_add_new_target)
        fab.setOnClickListener({
            val builder = AlertDialog.Builder(root.context)
            dialog = builder.setTitle(resources.getString(R.string.title_create_target))
                .setView(R.layout.target_add_record)
                .setPositiveButton(resources.getString(R.string.button_save),null)
                .setNegativeButton(resources.getString(R.string.button_cancel),null)
                .create()
            dialog.show()
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TargetsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}