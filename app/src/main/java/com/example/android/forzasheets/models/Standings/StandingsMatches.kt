package com.example.android.forzasheets.models.Standings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


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