package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player_goals")
class Goals(
    @ColumnInfo(name = "total")
    @SerializedName("total")
    val total: Int,

    @ColumnInfo(name = "assists")
    @SerializedName("assists")
    val assists: Int,

    @ColumnInfo(name = "saves")
    @SerializedName("saves")
    val saves: Int,

    @ColumnInfo(name = "conceded")
    @SerializedName("conceded")
    val conceded: Int
)