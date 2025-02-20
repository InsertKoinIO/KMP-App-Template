package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.native.PlatformComponent
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Provided
import org.koin.core.scope.Scope

@Module
actual class NativeModule {

    @Factory
    actual fun nativeComponent(@Provided scope : Scope) : PlatformComponent = PlatformComponent()
}