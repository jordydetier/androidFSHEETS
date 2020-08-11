package com.example.android.forzasheets.models.standing

import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved Standings
 *
 * This class is used to represent the Standings retrieved from the API
 *
 * @param [rank] place where the team stands in the table
 * @param [id] unique id to represent each standings object
 * @param [name] name of the team
 * @param [logo] imageURL for the team logo
 * @param [forme] form the team is in
 * @param [points] points earned
 * @param [matchesInfo] object that has more info about the matches played
 * @param [goalsDiff] goal difference
 * @param [leagueId] unique id to represent which league the team plays in
 */

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
    @Embedded
    val matchesInfo: StandingsMatches,

    @ColumnInfo(name = "goals_diff")
    @SerializedName("goalsDiff")
    val goalsDiff: Int,

    @ColumnInfo(name = "league_id")
    var leagueId: String
)

