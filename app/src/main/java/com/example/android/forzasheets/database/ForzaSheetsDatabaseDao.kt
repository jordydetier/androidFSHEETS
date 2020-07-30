package com.example.android.forzasheets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.models.team.Team

@Dao
interface ForzaSheetsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(standings: Standings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Standings>)

    @Query("SELECT * FROM standings ORDER BY team_name")
    fun getAllStandings(): LiveData<List<Standings>>

    @Query("SELECT * FROM standings WHERE league_id = :leagueId ORDER BY rank")
    fun getAllStandingsFromOneLeague(leagueId : Int): LiveData<List<Standings>>

    @Query("DELETE FROM standings")
    fun clear()

}