package com.example.android.forzasheets.ui.teamDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * TeamDetails' ViewModelFactory
 *
 * This Factory is used to create a viewmodel with specific parameters
 *
 * @param [teamId] the id of the chosen team which will be used by the viewmodel
 *
 */
class TeamDetailsViewModelFactory(private val teamId: String) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailsViewModel::class.java)) {
            return TeamDetailsViewModel(teamId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}