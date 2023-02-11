package com.example.pujagoodiesevaluation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.example.pujagoodiesevaluation.models.mostwatched.ChannelVideoList
import com.example.pujagoodiesevaluation.data.Repository
import com.example.pujagoodiesevaluation.data.database.ChannelEntity
import com.example.pujagoodiesevaluation.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/*The class is annotated with @HiltViewModel to signal to Hilt that it requires dependency injection. Hilt will then automatically manage the dependencies for this class, including the Repository object.*/
@HiltViewModel//HiltViewModel annotation is used on a ViewModel class to signal to Hilt that this class requires dependency injection. Hilt will then automatically manage the dependencies for this class. This way, you don't have to worry about manually managing the dependencies in your ViewModel. Instead, Hilt takes care of providing the dependencies needed by the ViewModel, allowing you to focus on writing your business logic.
class MainViewModel @Inject constructor(
    private val repository: Repository, application: Application) : AndroidViewModel(application) {
    //Room Database

    val readChannels: LiveData<List<ChannelEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertChannels(channelEntity: ChannelEntity)=
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertChannels(channelEntity)
    }

    var allchannels= ArrayList<ChannelVideoList>()

    //NetworkResult is a sealed class that is used to represent the result of a network operation, such as making a request to an API. It has three subclasses
    var channelResponse: MutableLiveData<NetworkResult<ChannelVideoList>> = MutableLiveData()

    fun getChannel(queries:Map<String,String>)= viewModelScope.launch {
        getChannelSafeCall(queries)
    }

    private suspend fun getChannelSafeCall(queries: Map<String, String>) {
        channelResponse.value=NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {

                val response = repository.remote.getChannel(queries)
                channelResponse.value = handleChannelResponse(response)

                val channelcache=channelResponse.value!!.data
                if(channelcache!=null){
                    offlinecacheChannels(channelcache)
                }

            }catch (e: Exception) {
                channelResponse.value = NetworkResult.Error("Weather error not found.")
            }
        } else {
            channelResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun offlinecacheChannels(channelcache: ChannelVideoList) {
        val channelEntity=ChannelEntity(channelcache)
        insertChannels(channelEntity)

    }

    private fun handleChannelResponse(response: Response<ChannelVideoList>): NetworkResult<ChannelVideoList>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val channels = response.body()
                if(channels != null) {
                    return NetworkResult.Success(channels)
                } else {
                    return NetworkResult.Error("No data found.")
                }
            }

            else -> {
                return NetworkResult.Error(response.message())
            }
        }

    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}