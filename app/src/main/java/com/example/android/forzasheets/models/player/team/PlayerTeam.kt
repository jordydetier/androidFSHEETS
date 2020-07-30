package com.example.android.forzasheets.models.player.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player_team")
class PlayerTeam (
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