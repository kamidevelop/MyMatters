package com.example.mymatters.incomedemand

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymatters.R

class IncomeDemandFragment : Fragment() {

    companion object {
        fun newInstance() = IncomeDemandFragment()
    }

    private lateinit var viewModel: IncomeDemandViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_demand, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IncomeDemandViewModel::class.java)
        // TODO: Use the ViewModel
    }

}