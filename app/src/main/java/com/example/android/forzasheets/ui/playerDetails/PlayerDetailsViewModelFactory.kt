package com.example.android.forzasheets.ui.playerDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerDetailsViewModelFactory (private val playerId: String) : ViewModelProvider.Factory {

    /**
     * Creates a new ViewModel of type [modelClass]
     *
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerDetailsViewModel::class.java)) {
            return PlayerDetailsViewModel(playerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}