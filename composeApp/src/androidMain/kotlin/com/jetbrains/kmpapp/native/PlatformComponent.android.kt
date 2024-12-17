package com.jetbrains.kmpapp.native

import android.content.Context

actual class PlatformComponent(val context: Context){
    
    actual fun sayHello() : String = "I'm Android - $context"
}