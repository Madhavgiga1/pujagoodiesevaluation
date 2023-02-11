package com.example.pujagoodiesevaluation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pujagoodiesevaluation.adapters.MostWatchedVidAdapter
import com.example.pujagoodiesevaluation.databinding.FragmentDisplayBinding
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList


class DisplayFragment : Fragment() {
    private var _binding:FragmentDisplayBinding?=null
    private val binding get()=_binding!!
    private val mAdapter: MostWatchedVidAdapter by lazy { MostWatchedVidAdapter(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding= FragmentDisplayBinding.inflate(layoutInflater,container,false)
        val args = arguments
        val myBundle:ChannelVideoList? = args?.getParcelable("channelBundle")
        setupRecyclerView(myBundle!!)

        return binding.root
    }
    private fun setupRecyclerView(channelVideoList: ChannelVideoList) {
        binding.IngredientsRecyclerView.adapter = mAdapter
        binding.IngredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter.videos=channelVideoList.items
    }



}