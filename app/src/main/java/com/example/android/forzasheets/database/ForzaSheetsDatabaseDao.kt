package com.example.android.forzasheets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.forzasheets.models.standing.Standings

/**
 * Interface which contains methods to insert, retrieve and clear database records
 *
 * This interface contains methods to insert Standings ans retrieve Standings
 */
@Dao
interface ForzaSheetsDatabaseDao {

    /**
     * Inserts a all [Standings] in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(standings: List<Standings>)

    /**
     * Returns a list of [Standings], filtered with leagueId and ordered by rank
     */
    @Query("SELECT * FROM standings WHERE league_id = :leagueId ORDER BY CAST(rank AS INTEGER)")
    fun getAllStandingsFromOneLeague(leagueId: String): LiveData<List<Standings>>

    /**
     * Removes all [Standings] from the database
     */
    @Query("DELETE FROM standings")
    fun clear()

}