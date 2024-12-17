package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.native.PlatformComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule : Module = module {
    single { PlatformComponent(androidContext()) }
}