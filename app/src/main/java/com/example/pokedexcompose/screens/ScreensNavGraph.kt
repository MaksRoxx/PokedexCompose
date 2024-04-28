package com.example.pokedexcompose.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.pokedexcompose.screens.home.BottomBarScreen
import com.example.pokedexcompose.screens.pokemondetail.PokemonDetailScreen
import com.example.pokedexcompose.screens.pokemonlist.PokemonListScreen
import com.example.pokedexcompose.screens.universal.ScreenContent
import java.util.Locale

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "home_graph",
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(AuthScreen.ListScreen.route) {
            PokemonListScreen(navController = navController)
        }
        composable(
            "${AuthScreen.DetailScreen.route}/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController
            )
        }
        authNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AuthScreen.Auth.route,
        startDestination = AuthScreen.SingIn.route
    ) {
        composable(route = AuthScreen.SingIn.route) {
            ScreenContent(name = AuthScreen.SingIn.route) {

            }
        }
        composable(route = AuthScreen.SingUp.route) {
            ScreenContent(name = AuthScreen.SingUp.route) {

            }
        }
        composable(route = AuthScreen.Profile.route) {
            ScreenContent(name = AuthScreen.Profile.route) {

            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    object ListScreen : AuthScreen(route = "pokemon_list_screen")
    object DetailScreen : AuthScreen(route = "pokemon_detail_screen")
    object Auth : AuthScreen(route = "auth")
    object Profile : AuthScreen(route = "profile")
    object SingIn : AuthScreen(route = "singIn")
    object SingUp : AuthScreen(route = "singUp")
}