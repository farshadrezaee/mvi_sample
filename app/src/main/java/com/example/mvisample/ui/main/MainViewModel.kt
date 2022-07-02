package com.example.mvisample.ui.main

import androidx.lifecycle.viewModelScope
import com.example.mvisample.base.BaseViewModel
import com.example.mvisample.base.DataState
import com.example.mvisample.base.Resource
import com.example.mvisample.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    BaseViewModel<MainViewState, MainViewEffect>() {

    fun action(intent: MainIntent) {
        when (intent) {
            is MainIntent.GetCharactersEvent -> getAllCharacters()
            is MainIntent.OnItemClickEvent -> onCharacterItemClick(intent.character.id)
        }
    }

    private fun onCharacterItemClick(id: Int) {
        _viewEffects.value = MainViewEffect.NavigateToDetailScreen(id)
    }

    private fun getAllCharacters() {

        val currentState = getViewState()
        _dataStates.value = DataState.Loading(currentState)

        viewModelScope.launch(defaultDispatchers) {

            characterRepository.getAllCharacters().collect {
                when (it) {

                    is Resource.Error -> {
                        _dataStates.postValue(DataState.Error(currentState, it.failure))
                    }

                    is Resource.Success -> {
                        val newState = MainViewState(it.data)
                        _dataStates.postValue(DataState.Success(newState))
                    }

                }
            }

        }

    }

}