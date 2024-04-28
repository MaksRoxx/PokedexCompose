package com.example.pokedexcompose.repository

import com.example.pokedexcompose.util.Resourse
import com.example.pokedexcompose.data.remote.responses.Pokemon
import com.example.pokedexcompose.data.remote.responses.PokemonList
import com.example.pokedexcompose.data.remote.PokeApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resourse<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resourse.Error("An unknown error occured.")
        }
        return Resourse.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resourse<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resourse.Error("An unknown error occured.")
        }
        return Resourse.Success(response)
    }
}