package com.example.mvisample.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvisample.base.Constant
import com.example.mvisample.base.DataState
import com.example.mvisample.databinding.ActivityMainBinding
import com.example.mvisample.ktx.gone
import com.example.mvisample.ktx.visible
import com.example.mvisample.ui.detail.CharacterDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var characterAdapter: CharacterAdapter

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        mainViewModel.action(MainIntent.GetCharactersEvent)
        observeDataStateLiveData()
        observeViewEffectLiveData()

    }


    private fun initViews() {

        characterAdapter = CharacterAdapter { character ->
            mainViewModel.action(MainIntent.OnItemClickEvent(character))
        }

        binding.apply {

            recyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = characterAdapter

        }

    }

    private fun observeDataStateLiveData() {

        mainViewModel.dataStates.observe(this) {

            when (it) {

                is DataState.Loading -> {
                    binding.progressBar.visible()
                }

                is DataState.Success -> {

                    binding.progressBar.gone()

                    it.data?.allCharacterResult?.let {
                        characterAdapter.loadData(it.characters)
                    }

                }

                is DataState.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, "error network!", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun observeViewEffectLiveData() {

        mainViewModel.viewEffects.observe(this) { mainViewEffect ->

            when (mainViewEffect) {

                is MainViewEffect.NavigateToDetailScreen -> {
                    navigateToDetailScreen(mainViewEffect.characterId)
                }

            }

        }

    }

    private fun navigateToDetailScreen(characterId: Int) {

        val intent = Intent(applicationContext, CharacterDetailActivity::class.java).apply {
            putExtra(Constant.Intent.CHARACTER_ID, characterId)
        }

        startActivity(intent)

    }


}