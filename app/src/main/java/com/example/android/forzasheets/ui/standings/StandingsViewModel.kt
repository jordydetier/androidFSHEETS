package com.example.android.forzasheets.ui.standings

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.helper.NetworkManager
import com.example.android.forzasheets.repository.ForzaDatabaseRepository
import kotlinx.coroutines.*


class StandingsViewModel(application: Application, leagueId: String) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _leagueId = MutableLiveData<String>()
    private val manager = NetworkManager(application.applicationContext)
    private val database = ForzaSheetsDatabase.getInstance(application)
    private val repository = ForzaDatabaseRepository(database = database)

    init {
        if (manager.isConnectedToInternet!!) {
            viewModelScope.launch {
                repository.refreshStandings(leagueId)
            }
        }
        _leagueId.value = leagueId
    }

    val standings = repository.getStandingsFromOneLeague(leagueId)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}