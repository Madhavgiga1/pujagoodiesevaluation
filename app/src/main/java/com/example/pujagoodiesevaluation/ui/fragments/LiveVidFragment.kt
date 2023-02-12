package com.example.pujagoodiesevaluation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.pujagoodiesevaluation.databinding.FragmentLiveVidBinding
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList

class LiveVidFragment : Fragment() {
    private var _binding:FragmentLiveVidBinding?=null
    private val binding get()=_binding!!

     var channelid:String?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val args = arguments
        val myBundle: ChannelVideoList? = args?.getParcelable("channelBundle")
        _binding= FragmentLiveVidBinding.inflate(layoutInflater,container,false)
        if (myBundle != null) {
            binding.liveVidThumbnail.load(myBundle.items.first().snippet.thumbnails.high.url){
                crossfade(600)
            }
        }


        return binding.root
    }




}