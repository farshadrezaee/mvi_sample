package com.example.mvisample.ui.main

sealed class MainViewEffect {

    class NavigateToDetailScreen(val characterId: Int) : MainViewEffect()

}