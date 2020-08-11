package com.example.android.forzasheets.ui.standings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.forzasheets.ui.teamDetails.TeamDetailsViewModel

/**
 * Standings' ViewModelFactory
 *
 * This Factory is used to create a viewmodel with specific parameters
 *
 * @param [application] a reference to the current application,  by the viewmodel for AndroidViewModel()
 * @param [leagueId] the id of the selected league which will be used by the viewmodel
 *
 */
class StandingsViewModelFactory(
    private val application: Application,
    private val leagueId: String
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StandingsViewModel::class.java)) {
            return StandingsViewModel(application, leagueId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}