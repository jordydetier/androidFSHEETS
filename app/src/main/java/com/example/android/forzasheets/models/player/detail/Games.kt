package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player_games")
class Games(
    @ColumnInfo(name = "appearances")
    @SerializedName("appearences")
    val appearances: Int,

    @ColumnInfo(name = "minutes_played")
    @SerializedName("minutes_played")
    val minutesPlayed: Int
)