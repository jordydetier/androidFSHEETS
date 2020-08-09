package com.example.android.forzasheets.models.standing

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "standings")
data class Standings(

    @ColumnInfo(name = "rank")
    @SerializedName("rank")
    val rank: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "team_id")
    @SerializedName("team_id")
    val id: Int,

    @ColumnInfo(name = "team_name")
    @SerializedName("teamName")
    val name: String,

    @ColumnInfo(name = "group")
    @SerializedName("group")
    val group: String,

    @ColumnInfo(name = "logo")
    @SerializedName("logo")
    val logo: String,

    @ColumnInfo(name = "forme")
    @SerializedName("forme")
    val forme: String,

    @ColumnInfo(name = "points")
    @SerializedName("points")
    val points: Int,

    @SerializedName("all")
    @Embedded()
    val matchesInfo: StandingsMatches,

    @ColumnInfo(name = "goals_diff")
    @SerializedName("goalsDiff")
    val goalsDiff: Int,

    @ColumnInfo(name = "league_id")
    var leagueId: String
)

