package com.example.mymatters.family

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymatters.R

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_family, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FamilyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}