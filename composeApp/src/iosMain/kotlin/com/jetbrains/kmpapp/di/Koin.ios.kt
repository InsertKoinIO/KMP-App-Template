package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.native.PlatformComponent
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Provided
import org.koin.core.scope.Scope

@Module
@ComponentScan("com.jetbrains.kmpapp.native")
actual class NativeModule