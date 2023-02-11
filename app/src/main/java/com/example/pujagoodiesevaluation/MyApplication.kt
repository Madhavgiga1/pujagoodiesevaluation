package com.example.pujagoodiesevaluation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp//nnotation is used on the application class of your Android app to signal to Hilt that this class is the entry point for dependency injection The @HiltAndroidApp annotation sets up the Hilt components needed for the entire application.
class MyApplication: Application() {


}