package com.example.mymatters.cardday

import android.content.Context
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
class CardDayRecycleVH(itemView: View) : RecyclerView.ViewHolder(itemView)
class CardDayRecycleViewAdapter : RecyclerView.Adapter<CardDayRecycleVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDayRecycleVH =
        CardDayRecycleVH(LayoutInflater.from(parent.context).inflate(R.layout.cardday_info_category_item,parent,false))

    override fun onBindViewHolder(holder: CardDayRecycleVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

}

class CardDayPagerVH(itemView : View) : RecyclerView.ViewHolder(itemView)

class CardDayViewPagerAdapter : RecyclerView.Adapter<CardDayPagerVH>(){
    private lateinit var _labelsArray: Array<String>
    var labelsArray: Array<String>
        get() = _labelsArray
        set(value) {
            _labelsArray = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDayPagerVH = CardDayPagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.cardday_info_item, parent, false))

    override fun onBindViewHolder(holder: CardDayPagerVH, position: Int) = holder.itemView.run{
        this.findViewById<TextView>(R.id.info_text).text = labelsArray[position]
    }

    override fun getItemCount(): Int {
        return labelsArray.size
    }

}

class CardDayFragment : Fragment() {

    companion object {
        fun newInstance() = CardDayFragment()
    }

    private lateinit var viewModel: CardDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.fragment_card_day, container, false)
        val vp = inf.findViewById<ViewPager2>(R.id.cardday_info_viewpager)
        val cdAdapter = CardDayViewPagerAdapter()
        cdAdapter.labelsArray = resources.getStringArray(R.array.titles_cardday)
        vp.adapter = cdAdapter
        return inf
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}