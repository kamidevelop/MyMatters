package com.example.mymatters.targets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymatters.R

class TargetsFragment : Fragment() {

    companion object {
        fun newInstance() = TargetsFragment()
    }

    private lateinit var viewModel: TargetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_targets, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TargetsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}