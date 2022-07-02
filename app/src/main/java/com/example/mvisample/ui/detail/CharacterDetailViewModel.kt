package com.example.mvisample.ui.detail

import androidx.lifecycle.viewModelScope
import com.example.mvisample.base.BaseViewModel
import com.example.mvisample.base.DataState
import com.example.mvisample.base.Resource
import com.example.mvisample.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    BaseViewModel<CharacterDetailViewState, CharacterDetailViewEffect>() {

    fun action(intent: CharacterDetailIntent) {
        when (intent) {
            is CharacterDetailIntent.GetCharacterDetailEvent -> getCharacterDetail(intent.id)
        }
    }

    private fun getCharacterDetail(id: Int) {

        val currentState = getViewState()
        _dataStates.value = DataState.Loading(currentState)

        viewModelScope.launch(defaultDispatchers) {

            characterRepository.getCharacter(id).collect {
                when (it) {

                    is Resource.Error -> {
                        _dataStates.postValue(DataState.Error(currentState, it.failure))
                    }

                    is Resource.Success -> {
                        val newState = CharacterDetailViewState(it.data)
                        _dataStates.postValue(DataState.Success(newState))
                    }

                }
            }

        }


    }

}