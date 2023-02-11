package com.example.pujagoodiesevaluation.utils
/*NetworkResult is a sealed class that is used to represent the result of a network operation, such as making a request to an API. It has three subclasses:
   Success: This represents a successful network operation and holds the data returned from the API in the data property.

    Error: This represents a failed network operation and holds an error message in the message property, as well as the data returned from the API (if any) in the data property.

    Loading: This represents a network operation that is still in progress and doesn't have any data or error message yet.*/
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Loading<T>: NetworkResult<T>()

}