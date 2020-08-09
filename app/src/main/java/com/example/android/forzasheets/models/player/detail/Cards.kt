package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player_cards")
class Cards(
    @ColumnInfo(name = "yellow")
    @SerializedName("yellow")
    val yellow: Int,

    @ColumnInfo(name = "red")
    @SerializedName("red")
    val red: Int
)