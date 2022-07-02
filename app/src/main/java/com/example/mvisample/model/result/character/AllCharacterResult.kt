package com.example.mvisample.model.result.character

import com.google.gson.annotations.SerializedName

data class AllCharacterResult(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)