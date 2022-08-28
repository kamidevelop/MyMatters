package com.example.mymatters.incomedemand

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

    private lateinit var viewModel: IncomeDemandViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_income_demand, container, false)
        val vp = root.findViewById<ViewPager2>(R.id.income_demand_viewpager)
        val idAdapter = IncomeDemandViewPagerAdaper()
        idAdapter.labels = arrayOf(resources.getString(R.string.title_income),resources.getString(R.string.title_demand))
        vp.adapter = idAdapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IncomeDemandViewModel::class.java)
        // TODO: Use the ViewModel
    }

}