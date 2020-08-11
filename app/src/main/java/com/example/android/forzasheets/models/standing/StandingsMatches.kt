package com.example.android.forzasheets.models.standing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * The class represents the retrieved standings
 *
 * This class is used to represent the matches info retrieved from the API
 *
 * @param [matchesId] unique id to represent each standingsMatches object
 * @param [matchesPlayed] number of matches played
 * @param [matchesWon] number of matches won
 * @param [matchesDrawn] number of matches drawn
 * @param [matchesLost] number of matches lost
 */
@Entity(tableName = "matches")
data class StandingsMatches(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "matches_id")
    val matchesId: Int,

    @ColumnInfo(name = "matches_played")
    @SerializedName("matchsPlayed")
    val matchesPlayed: Int,

    @ColumnInfo(name = "matches_won")
    @SerializedName("win")
    val matchesWon: Int,

    @ColumnInfo(name = "matches_drawn")
    @SerializedName("draw")
    val matchesDrawn: Int,

    @ColumnInfo(name = "matches_lost")
    @SerializedName("lost")
    val matchesLost: Int,

    @ColumnInfo(name = "goals_for")
    @SerializedName("goalsFor")
    val goalsFor: Int,

    @ColumnInfo(name = "goals_against")
    @SerializedName("goalsAgainst")
    val goalsAgainst: Int
)