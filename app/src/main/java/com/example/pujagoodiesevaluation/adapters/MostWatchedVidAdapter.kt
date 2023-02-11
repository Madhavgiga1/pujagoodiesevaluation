package com.example.pujagoodiesevaluation.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pujagoodiesevaluation.databinding.VideoRowLayoutBinding
import com.example.pujagoodiesevaluation.models.mostwatched.Item
import com.example.pujagoodiesevaluation.utils.Constants.Companion.API_KEY
import com.google.android.youtube.player.YouTubeStandalonePlayer

class MostWatchedVidAdapter(private val context: Context):RecyclerView.Adapter<MostWatchedVidAdapter.MyViewHolder>() {
    var videos=emptyList<Item>()
    class MyViewHolder(val binding: VideoRowLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: Item){
            binding.video=result
            //binding.chaninfo=result.items.first().snippet


            binding.executePendingBindings()

        }


        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding= VideoRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentvideo = videos[position]
        holder.bind(currentvideo)
        holder.binding.VideoRowLayout.setOnClickListener {
            /*val intent = Intent(context,YoutubeActivity::class.java)
            intent.putExtra("video_id", currentvideo.id.videoId)
            context.startActivity(intent)*/
            val videoId = videos[position].id.videoId //get the video id from the list
            val context = holder.itemView.context //get the context from the viewholder
            val intent = YouTubeStandalonePlayer.createVideoIntent(context as Activity?, API_KEY, videoId) //create an intent with the video id and your API key
            context.startActivity(intent) //start the activity with the created intent
        }




    }

    override fun getItemCount(): Int {
        return videos.size

    }
}