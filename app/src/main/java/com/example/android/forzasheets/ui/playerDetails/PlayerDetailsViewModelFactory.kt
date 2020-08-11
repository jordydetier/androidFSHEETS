package com.example.android.forzasheets.ui.playerDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * PlayerDetails' ViewModelFactory
 *
 * This Factory is used to create a viewmodel with specific parameters
 *
 * @param [playerId] the id of the chosen player which will be used by the viewmodel
 *
 */
class PlayerDetailsViewModelFactory(private val playerId: String) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerDetailsViewModel::class.java)) {
            return PlayerDetailsViewModel(playerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}