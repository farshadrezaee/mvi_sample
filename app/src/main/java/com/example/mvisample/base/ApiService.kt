package com.example.mvisample.base

import com.example.mvisample.model.result.character.AllCharacterResult
import com.example.mvisample.model.result.character.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<AllCharacterResult>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

}