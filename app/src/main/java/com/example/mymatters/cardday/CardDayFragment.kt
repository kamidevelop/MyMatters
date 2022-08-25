package com.example.mymatters.cardday

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
class CardDayPagerVH(itemView : View) : RecyclerView.ViewHolder(itemView)

class CardDayViewPagerAdapter : RecyclerView.Adapter<CardDayPagerVH>(){
    val texts = arrayListOf<CharSequence>("Тут будет рейтинг","Тут будут расходы","Тут будут доходы")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDayPagerVH =
        CardDayPagerVH(LayoutInflater.from(parent.context).inflate(R.layout.cardday_info_item,parent,false))

    override fun onBindViewHolder(holder: CardDayPagerVH, position: Int) = holder.itemView.run{
        this.findViewById<TextView>(R.id.info_text).text = texts[position]
    }

    override fun getItemCount(): Int {
        return texts.size
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
        vp.adapter = CardDayViewPagerAdapter()
        return inf
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}