package com.example.pujagoodiesevaluation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pujagoodiesevaluation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Pujagoodiesevaluation)
        setContentView(R.layout.activity_main)
    }
}