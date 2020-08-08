package com.example.android.forzasheets.ui.standings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.forzasheets.ui.teamDetails.TeamDetailsViewModel

class StandingsViewModelFactory(private val application: Application, private val leagueId: String) : ViewModelProvider.Factory {

    /**
     * Creates a new ViewModel of type [modelClass]
     *
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StandingsViewModel::class.java)) {
            return StandingsViewModel(application,leagueId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}