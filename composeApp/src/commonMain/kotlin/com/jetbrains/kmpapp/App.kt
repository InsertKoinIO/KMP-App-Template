package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmpapp.data.IdGenerator
import com.jetbrains.kmpapp.di.appModule
import com.jetbrains.kmpapp.di.diConfig
import com.jetbrains.kmpapp.native.PlatformComponent
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.list.ListScreen
import org.koin.compose.KoinApplication
import org.koin.compose.getKoin
import org.koin.core.Koin
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf

@Composable
fun App() {
    KoinApplication(
        application = diConfig()
    ) {
        MaterialTheme {
            val navController: NavHostController = rememberNavController()
            NavHost(
                navController,
                startDestination = "list"
            ) {
                composable("list") { backStackEntry ->
                    ListScreen(navController)
                }
                composable("detail/{objectId}") { backStackEntry ->
                    val objectId = backStackEntry.arguments?.getString("objectId")?.toInt()
                    DetailScreen(navController, objectId!!)
                }
            }
        }

        val koin = getKoin()
        runExample(koin)
    }
}

internal fun runExample(koin: Koin) {
    val hello = koin.get<PlatformComponent>().sayHello()
    println(hello)
    val idGen = koin.get<IdGenerator> { parametersOf("_prefix_") }.generate()
    println("Id => $idGen")
}
