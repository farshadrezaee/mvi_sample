package com.example.mvisample.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel<T, U>(val defaultDispatchers: CoroutineDispatcher = Dispatchers.IO) :
    ViewModel() {

    protected val _dataStates: MutableLiveData<DataState<T>> = MutableLiveData()
    val dataStates: LiveData<DataState<T>> = _dataStates

    protected val _viewEffects: SingleLiveEvent<U> = SingleLiveEvent()
    val viewEffects: LiveData<U> = _viewEffects

    protected fun getViewState(): T? = _dataStates.value?.toData()

}