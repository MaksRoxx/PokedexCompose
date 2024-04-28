package com.example.pokedexcompose.screens.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.pokedexcompose.data.remote.responses.Pokemon
import com.example.pokedexcompose.util.Resourse
import com.example.pokedexcompose.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    suspend fun getPokemonInfo(pokemonName: String): Resourse<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}