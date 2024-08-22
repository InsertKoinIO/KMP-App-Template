package com.jetbrains.kmpapp.native

import android.content.Context
import org.koin.core.annotation.Single

@Single
actual class PlatformComponent(val context: Context){
    
    actual fun sayHello() : String = "I'm Android - $context"
}