package com.jetbrains.kmpapp.native

import org.koin.core.annotation.Single

@Single
actual class PlatformComponent {
    actual fun sayHello() : String = "I'm iOS"
}