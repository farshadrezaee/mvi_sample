package com.example.mvisample.ui.detail


sealed class CharacterDetailIntent {

    class GetCharacterDetailEvent(val id: Int) : CharacterDetailIntent()

}