package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved PlayerDetail
 *
 * This class is used to represent the Cards retrieved from the API
 *
 * @param [yellow] total amount of yellow cards a player has received
 * @param [red] total amount of red cards a player has received
 */
@Entity(tableName = "player_cards")
class Cards(
    @ColumnInfo(name = "yellow")
    @SerializedName("yellow")
    val yellow: Int,

    @ColumnInfo(name = "red")
    @SerializedName("red")
    val red: Int
)