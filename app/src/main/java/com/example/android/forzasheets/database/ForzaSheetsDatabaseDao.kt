package com.example.android.forzasheets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.forzasheets.models.standing.Standings

@Dao
interface ForzaSheetsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(standings: List<Standings>)

    @Query("SELECT * FROM standings ORDER BY CAST(rank AS INTEGER)")
    fun getAllStandings(): LiveData<List<Standings>>

    @Query("SELECT * FROM standings WHERE league_id = :leagueId ORDER BY CAST(rank AS INTEGER)")
    fun getAllStandingsFromOneLeague(leagueId: String): LiveData<List<Standings>>

    @Query("DELETE FROM standings")
    fun clear()

}