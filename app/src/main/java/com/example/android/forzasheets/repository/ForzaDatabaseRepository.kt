package com.example.android.forzasheets.repository

import androidx.lifecycle.LiveData
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.network.FootballApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class ForzaDatabaseRepository(private val database : ForzaSheetsDatabase){

    val standings: LiveData<List<Standings>> = database.forzaSheetsDatabaseDao.getAllStandings()

    suspend fun refreshStandings() {
        withContext(Dispatchers.IO) {
            val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "524").await().response.standings[0]
            database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
        }
    }
}