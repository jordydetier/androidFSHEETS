package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved PlayerDetail
 *
 * This class is used to represent the Games retrieved from the API
 *
 * @param [appearances] total amount of appearances from a player
 * @param [minutesPlayed] total amount of minutes a player has played
 */
@Entity(tableName = "player_games")
class Games(
    @ColumnInfo(name = "appearances")
    @SerializedName("appearences")
    val appearances: Int,

    @ColumnInfo(name = "minutes_played")
    @SerializedName("minutes_played")
    val minutesPlayed: Int
)