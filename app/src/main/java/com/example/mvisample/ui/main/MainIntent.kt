package com.example.mvisample.ui.main

import com.example.mvisample.model.result.character.Character

sealed class MainIntent {

    object GetCharactersEvent : MainIntent()

    class OnItemClickEvent(val character: Character) : MainIntent()

}