package com.example.mvisample.data.repository

import javax.inject.Inject

class CharacterRepository @Inject constructor() : BaseRepository() {

    suspend fun getAllCharacters() = fetchData {
        getAllCharacters()
    }

    suspend fun getCharacter(id: Int) = fetchData {
        getCharacter(id)
    }

}