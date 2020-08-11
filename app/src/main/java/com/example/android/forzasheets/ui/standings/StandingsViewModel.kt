package com.example.android.forzasheets.ui.standings

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.helper.NetworkManager
import com.example.android.forzasheets.repository.ForzaDatabaseRepository
import kotlinx.coroutines.*

/**
 * Standings' ViewModel
 *
 * This ViewModel contains the logic behind the MovieDetails fragment
 *
 * @param [application] a reference to the current application,  by the viewmodel for AndroidViewModel()
 * @param [leagueId] The id of the selected league

 *
 */
class StandingsViewModel(application: Application, leagueId: String) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Encapsulated id of the selected league
     *
     */
    private val _leagueId = MutableLiveData<String>()

    /**
     * Object of NetworkManager to help us know if the device is connected to the internet
     *
     */
    private val manager = NetworkManager(application.applicationContext)

    /**
     * Instance of the room database
     */
    private val database = ForzaSheetsDatabase.getInstance(application)

    /**
     * Repository to retrieve standings from the room database
     */
    private val repository = ForzaDatabaseRepository(database = database)

    /**
     * Gets called during the creation of the viewmodel,
     * allocates the passed league id to the property and
     * refreshes the room database with new standings if connected to the internet
     */
    init {
        if (manager.isConnectedToInternet!!) {
            viewModelScope.launch {
                repository.refreshStandings(leagueId)
            }
        }
        _leagueId.value = leagueId
    }

    /**
     * List of standings for the selected league
     */
    val standings = repository.getStandingsFromOneLeague(leagueId)


    /**
     * When the view is cleared the viewModelJob is cancelled
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}