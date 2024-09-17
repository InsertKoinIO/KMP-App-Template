package com.jetbrains.kmpapp.other.native

import org.koin.core.annotation.Single

@Single
actual class OtherPlatformComponent {
    actual fun sayGoodBye(): String = "Goodbye iOS!"
}