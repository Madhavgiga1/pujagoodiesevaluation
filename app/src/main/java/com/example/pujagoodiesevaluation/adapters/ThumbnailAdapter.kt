package com.example.pujagoodiesevaluation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.databinding.ChannelRowLayoutBinding

class ThumbnailAdapter:RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder>() {
    var channels=emptyList<ChannelVideoList>()
    class MyViewHolder(private val binding: ChannelRowLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(result:ChannelVideoList){
            binding.channel=result
            binding.chaninfo=result.items.first().snippet
            binding.executePendingBindings()

        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding=ChannelRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentchannel = channels[position]
            holder.bind(currentchannel)



    }

    override fun getItemCount(): Int {
        return channels.size

    }
}