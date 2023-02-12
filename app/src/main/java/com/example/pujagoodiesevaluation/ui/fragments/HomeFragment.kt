package com.example.pujagoodiesevaluation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.adapters.ThumbnailAdapter
import com.example.pujagoodiesevaluation.databinding.FragmentHomeBinding
import com.example.pujagoodiesevaluation.utils.Constants.Companion.API_KEY
import com.example.pujagoodiesevaluation.utils.NetworkResult
import com.example.pujagoodiesevaluation.viewmodels.MainViewModel
import com.google.android.gms.common.util.CollectionUtils.listOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!
    //var allchannels= ArrayList<ChannelVideoList>()
    private val mAdapter by lazy({ ThumbnailAdapter() })

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentHomeBinding.inflate(layoutInflater,container,false)


        setuprecyclerview()
        readDatabase()



        return binding.root
    }
    private fun prereq(){
        var channelids=listOf("UCRUAdVm9ZOF4JheOd8qIQHA","UCDe0DwkMVFfSIoiYdQUPQmQ","UCJeQx6mAyNdPUc9sJA866Xw","UCHq7ZxlzRRXimaBmk5QAxSQ","UCUUIz69kK3Ib5bD4hWLKAwA"
            ,"UCU_Qv0QDWtxv6D3fkZ-A0lw","UCCR5eciEJIMvX2o1tiYOUKQ","UCT_QwW7Tbew5qrYNb2auqAQ","UCOizxR3GwY7dmehMCAdvv9g","UCyIkg79GpPVF77qYKoAINtw",
            "UCDqkux3AH7P9hRjmunoUeAQ","UC7ZivIYRB0fMSGh-THcTYbw","UCaayLD9i5x4MmIoVZxXSv_g","UCHKGDg0GJKBsA9mFraDOLHA")
        channelids.forEach{
            requestAPIdata(applyqueries((it)))
        }
    }
    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readChannels.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    var channellist= ArrayList<ChannelVideoList>()
                    mainViewModel.readChannels.value?.forEach{
                        channellist.add(it.channelVideoList)

                    }
                    mAdapter.channels=channellist
                    setuprecyclerview()

                }
                else {
                    prereq()
                }
            })
        }
    }
    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readChannels.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    //mAdapter.setData(database.first().foodRecipe)
                }
            }
        }
    }



    private fun requestAPIdata(applyqueries: HashMap<String, String>) {
        Log.d("RecipesFragment", "readApi called!")
        mainViewModel.getChannel(applyqueries)
        mainViewModel.channelResponse.observe(viewLifecycleOwner,{ response->
            when(response){
                is NetworkResult.Success -> {
                    response.data?.let {
                        if (!mainViewModel.allchannels.contains(it)) {
                            //mainViewModel.allchannels.add(it)
                            setuprecyclerview()
                        }
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }
    private fun setuprecyclerview() {
        binding.recylerView.adapter=mAdapter
        //mAdapter.channels=mainViewModel.allchannels
        binding.recylerView.layoutManager = GridLayoutManager(activity, 2)
    }

    fun applyqueries(s:String):HashMap<String, String>{
        val queries: HashMap<String, String> = HashMap()
        queries["part"]="snippet"
        queries["order"]="viewCount"
        queries["type"]="video"
        queries["channelId"]=s
        queries["maxResults"]="8"
        queries["key"]=API_KEY
        return queries
    }

}