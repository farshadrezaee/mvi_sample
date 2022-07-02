package com.example.mvisample.base

sealed class DataState<out ViewState> {

    data class Success<ViewState>(val data: ViewState?) : DataState<ViewState>()
    data class Error<ViewState>(val data: ViewState?, val failure: Failure) : DataState<ViewState>()
    data class Loading<ViewState>(val data: ViewState?) : DataState<ViewState>()

    //Helper methods that make it easy to access the sealed class contents in xml data binding    
    fun toData(): ViewState? = when (this) {
        is Success -> this.data
        is Error -> this.data
        is Loading -> this.data
    }

    fun isLoading(): Boolean? = if (this is Loading) true else null

}