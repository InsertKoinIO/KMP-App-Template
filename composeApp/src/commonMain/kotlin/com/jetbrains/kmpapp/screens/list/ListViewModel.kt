package com.jetbrains.kmpapp.screens.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

class ListViewModel(
    savedStateHandle: SavedStateHandle,
    museumRepository: MuseumRepository
) : ViewModel() {

    init {
        val oldValue : String? = savedStateHandle.get<String>("test")
        val newValue = KoinPlatformTools.generateId()
        savedStateHandle["test"] = newValue
        println("$this - old: $oldValue - new: $newValue")
        println("$this - message? : ${savedStateHandle.get<String>("message")}")
    }

    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
