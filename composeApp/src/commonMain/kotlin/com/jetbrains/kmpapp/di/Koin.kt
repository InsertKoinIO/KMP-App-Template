package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.IdGenerator
import com.jetbrains.kmpapp.data.InMemoryMuseumStorage
import com.jetbrains.kmpapp.data.KtorMuseumApi
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.data.MuseumStorage
import com.jetbrains.kmpapp.native.PlatformComponent
import com.jetbrains.kmpapp.screens.detail.DetailViewModel
import com.jetbrains.kmpapp.screens.list.ListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


val dataModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single {
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(get(), contentType = ContentType.Any)
            }
        }
    }
    singleOf(::IdGenerator)
    singleOf(::KtorMuseumApi) bind MuseumApi::class
    singleOf(::MuseumRepository)
    singleOf(::InMemoryMuseumStorage) bind MuseumStorage::class
}

val viewModels = module {
    viewModelOf(::DetailViewModel)
    viewModelOf(::ListViewModel)
}

val appModule = module {
    includes(dataModule, viewModels, nativeModule)
}

//@Module(includes = [DataModule::class,ViewModelModule::class, NativeModule::class])
//class AppModule

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        modules(
            appModule,
        )
        config?.invoke(this)
    }
    val hello = KoinPlatform.getKoin().get<PlatformComponent>().sayHello()
    println(hello)

    val idGen = KoinPlatform.getKoin().get<IdGenerator> { parametersOf("_prefix_") }.generate()
    println("Id => $idGen")
}
