package com.example.android.forzasheets.helper

import android.util.Log
import com.example.android.forzasheets.database.ForzaSheetsDatabaseDao
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.network.FootballApi
import kotlinx.coroutines.*
import retrofit2.await

class InflateDatabase(val database: ForzaSheetsDatabaseDao) {
    private var getStandingsJob = Job()
    private val coroutineScope = CoroutineScope(getStandingsJob + Dispatchers.Main)

    fun inflateDb() {
        coroutineScope.launch {
            var getPremierLeagueStandingsDeferred =
                FootballApi.retrofitService.getStandings(leagueId = "524")
            val listResult = getPremierLeagueStandingsDeferred.await().response.standings[0]
            listResult.forEach { standing -> standing.leagueId = "524" }
            insert(listResult)
            var getBundesligaStandingsDeferred =
                FootballApi.retrofitService.getStandings(leagueId = "754")
            val listResult2 = getBundesligaStandingsDeferred.await().response.standings[0]
            listResult2.forEach { standing -> standing.leagueId = "754" }
            insert(listResult2)
        }
    }

    private suspend fun insert(standings: List<Standings>) {
        withContext(Dispatchers.IO) {
            try {
                database.insertAll(standings)
            } catch (e: Exception) {
                Log.e("dbError", e.message!!)
            }
        }
    }
}