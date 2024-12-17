package com.jetbrains.kmpapp.data

import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

class IdGenerator(private val prefix : String) {
    
    fun generate() : String = prefix + KoinPlatformTools.generateId()
}