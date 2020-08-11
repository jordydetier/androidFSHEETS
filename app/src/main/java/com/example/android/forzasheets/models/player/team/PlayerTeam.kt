package com.example.android.forzasheets.models.player.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved PlayerTeam
 *
 * This class is used to represent the PlayerTeam retrieved from the API
 *
 * @param [id] unique id to represent each playerTeam object
 * @param [playerName] full name of the player
 * @param [position] position of the player
 * @param [positionNumber] number to help sort players by position
 * @param [age] age of the player
 * @param [nationality] nationality of the player
 */
@Entity(tableName = "player_team")
class PlayerTeam(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "player_id")
    @SerializedName("player_id")
    val id: Int,

    @ColumnInfo(name = "player_name")
    @SerializedName("player_name")
    val playerName: String,

    @ColumnInfo(name = "position")
    @SerializedName("position")
    val position: String,

    @ColumnInfo(name = "position_number")
    var positionNumber: Int,

    @ColumnInfo(name = "age")
    @SerializedName("age")
    val age: Int,

    @ColumnInfo(name = "nationality")
    @SerializedName("nationality")
    val nationality: String

)