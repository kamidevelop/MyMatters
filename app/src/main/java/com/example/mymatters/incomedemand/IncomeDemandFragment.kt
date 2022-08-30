package com.example.mymatters.incomedemand

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mymatters.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncomeDemandViewPagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
class IncomeDemandViewPagerAdaper : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var _labels: Array<String>
    var labels : Array<String>
        get() = _labels
        set(value) {
            _labels = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        IncomeDemandViewPagerVH(LayoutInflater.from(parent.context).inflate(R.layout.cardday_info_item,parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.itemView.run{
        val infoText = findViewById<TextView>(R.id.info_text)
        infoText.text = labels[position]
    }

    override fun getItemCount(): Int = labels.size

}

class IncomeDemandFragment : Fragment() {

    companion object {
        fun newInstance() = IncomeDemandFragment()
    }
    private val layoutsIncomeDemand = arrayOf(R.layout.income_add_record,R.layout.demand_add_record)
    private val lambdas = arrayOf({dialogInter : DialogInterface, i : Int ->

    },{dialogInter : DialogInterface, i : Int ->

    })
    private lateinit var viewModel: IncomeDemandViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_income_demand, container, false)
        val vp = root.findViewById<ViewPager2>(R.id.income_demand_viewpager)
        val idAdapter = IncomeDemandViewPagerAdaper()
        val fab = root.findViewById<FloatingActionButton>(R.id.fab_add_income_demand)
        val lbs = arrayOf(resources.getString(R.string.title_income),resources.getString(R.string.title_demand))
        idAdapter.labels = lbs
        vp.adapter = idAdapter
        fab.setOnClickListener({
            val builder = AlertDialog.Builder(root.context)
            val dialog = builder.setTitle(lbs[vp.currentItem])
                .setView(layoutsIncomeDemand[vp.currentItem])
                .setPositiveButton(resources.getString(R.string.button_save), lambdas[vp.currentItem])
                .setNegativeButton(resources.getString(R.string.button_cancel),null).create()
            dialog.show()
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IncomeDemandViewModel::class.java)
        // TODO: Use the ViewModel
    }

}