package com.example.android.forzasheets.ui.teamDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TeamDetailsViewModelFactory(private val teamId: String) : ViewModelProvider.Factory {

    /**
     * Creates a new ViewModel of type [modelClass]
     *
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailsViewModel::class.java)) {
            return TeamDetailsViewModel(teamId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}