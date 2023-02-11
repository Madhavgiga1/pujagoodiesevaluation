package com.example.pujagoodiesevaluation.bindingadapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.models.mostwatched.Snippet
import com.example.pujagoodiesevaluation.ui.fragments.HomeFragmentDirections

class ChannelRowBinding {
    companion object{

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @BindingAdapter("onChannelClickListener")
        @JvmStatic
        fun onChannelClickListener(recipeRowLayout: ConstraintLayout, result:ChannelVideoList){
            recipeRowLayout.setOnClickListener{
                try {
                    val action=HomeFragmentDirections.actionHomeFragmentToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }


            }
        }

    }

}