package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import kotlinx.coroutines.flow.Flow
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val museumRepository: MuseumRepository
) : ViewModel() {

    init {
        val oldValue : String? = savedStateHandle.get<String>("test")
        val newValue = KoinPlatformTools.generateId()
        savedStateHandle["test"] = newValue
        println("$this - old: $oldValue - new: $newValue")
        println("$this - objectId: ${savedStateHandle.get<String>("objectId")}")
    }

    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
