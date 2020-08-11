package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved PlayerDetail
 *
 * This class is used to represent the Goals retrieved from the API
 *
 * @param [total] total amount of goals from a player
 * @param [assists] total amount of assists from a player
 * @param [saves] total amount of saves made by a goalkeeper
 * @param [conceded] total amount of goals conceded by a goalkeeper
 */
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