package com.example.mvisample.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mvisample.base.Constant
import com.example.mvisample.base.DataState
import com.example.mvisample.databinding.ActivityDetailBinding
import com.example.mvisample.ktx.gone
import com.example.mvisample.ktx.loadImage
import com.example.mvisample.ktx.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val characterDetailViewModel by viewModels<CharacterDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCharacterDetail()
        observeDataStateLiveData()
        observeViewEffectLiveData()

    }

    private fun getCharacterDetail() {
        val characterId = intent.getIntExtra(Constant.Intent.CHARACTER_ID, 0)
        characterDetailViewModel.action(CharacterDetailIntent.GetCharacterDetailEvent(characterId))
    }

    private fun observeDataStateLiveData() {

        characterDetailViewModel.dataStates.observe(this) {

            when (it) {


                is DataState.Loading -> {
                    binding.progressBar.visible()
                    binding.contentLayout.gone()
                }

                is DataState.Success -> {

                    binding.progressBar.gone()
                    binding.contentLayout.visible()

                    it.data?.character?.let {
                        binding.apply {
                            imageView.loadImage(it.image)
                            nameTextView.text = it.name
                            genderTextView.text = it.gender
                            locationTextView.text = it.location.name
                            statusTextView.text = it.status
                            speciesTextView.text = it.species
                        }
                    }

                }

                is DataState.Error -> {
                    binding.progressBar.gone()
                    binding.contentLayout.gone()
                    Toast.makeText(this, "error network!", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun observeViewEffectLiveData() {

        characterDetailViewModel.viewEffects.observe(this) {

        }

    }

}