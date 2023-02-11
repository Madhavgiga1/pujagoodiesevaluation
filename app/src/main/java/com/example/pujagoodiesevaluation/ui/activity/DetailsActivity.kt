package com.example.pujagoodiesevaluation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.pujagoodiesevaluation.R
import com.example.pujagoodiesevaluation.adapters.PtagerAdapter
import com.example.pujagoodiesevaluation.databinding.ActivityDetailsBinding
import com.example.pujagoodiesevaluation.ui.fragments.DisplayFragment
import com.example.pujagoodiesevaluation.ui.fragments.LiveVidFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()
    //val channelVideos = args.channelVideos

    /*  a Kotlin class that is using the navArgs delegate to retrieve arguments that have been passed to an activity. The navArgs delegate is a tool provided by
     the Android Navigation component that allows you to easily retrieve and use arguments that have been passed to an activity or fragment using the Android Navigation component.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tabLayout = findViewById<(TabLayout)>(R.id.tabLayout)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(DisplayFragment())
        fragments.add(LiveVidFragment())
        val args = intent.extras?.let { DetailsActivityArgs.fromBundle(it) }
        val videos = args?.videos


        val titles = ArrayList<String>()
        titles.add("Most Watched")
        titles.add("Live Video")

        //Log.d("DetailsActivity", "channelVideos = ${args.channelVideos}")
        val resultBundle = Bundle()
        resultBundle.putParcelable("channelBundle", videos)
         //resultBundle = intent.getBundleExtra("resultBundle")
        //val channelBundle = resultBundle?.getParcelable<ChannelVideoList>("channelBundle")


        val adapter = PtagerAdapter(resultBundle, fragments, titles, supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(binding.detailsLayout, message, Snackbar.LENGTH_SHORT).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this, color))
    }

}