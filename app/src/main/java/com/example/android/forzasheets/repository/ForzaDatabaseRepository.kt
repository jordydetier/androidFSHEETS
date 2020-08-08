package com.example.android.forzasheets.repository


import androidx.lifecycle.LiveData
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.network.FootballApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await


class ForzaDatabaseRepository(private val database : ForzaSheetsDatabase){

    val standings:  LiveData<List<Standings>> = database.forzaSheetsDatabaseDao.getAllStandings()

    fun getStandingsFromOneLeague(leagueId: String): LiveData<List<Standings>> {
        return database.forzaSheetsDatabaseDao.getAllStandingsFromOneLeague(leagueId = leagueId)
    }
    suspend fun refreshStandings(leagueId : String) {
        withContext(Dispatchers.IO) {
            when(leagueId){
                "524" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "524").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "524" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
                "775" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "775").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "775" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
                "891" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "891").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "891" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
                "754" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "754").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "754" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
                "525" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "525").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "525" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
                "566" ->{
                    val standingsPremierLeague = FootballApi.retrofitService.getStandings(leagueId = "566").await().response.standings[0].toMutableList()
                    standingsPremierLeague.forEach { standing -> standing.leagueId = "566" }
                    database.forzaSheetsDatabaseDao.insertAll(standingsPremierLeague)
                }
            }

        }
    }

}