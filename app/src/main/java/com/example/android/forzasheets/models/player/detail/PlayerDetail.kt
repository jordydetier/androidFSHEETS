package com.example.android.forzasheets.models.player.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved PlayerDetail
 *
 * This class is used to represent the PlayerDetail retrieved from the API
 *
 * @param [id] unique id to represent each playerTeam object
 * @param [firstName] first name of the player
 * @param [lastName] last name of the player
 * @param [position] position of the player
 * @param [age] age of the player
 * @param [birthDate] birth date of the player
 * @param [nationality] nationality of the player
 * @param [height] height of the player
 * @param [weight] weight of the player
 * @param [goals] object that has more info about everything to do with goals
 * @param [cards] object that has more info about everything to do with cards
 * @param [games] object that has more info about everything to do with games played
 */
@Entity(tableName = "player_detail")
class PlayerDetail(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "player_id")
    @SerializedName("player_id")
    val id: Int,

    @ColumnInfo(name = "first_name")
    @SerializedName("firstname")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    @SerializedName("lastname")
    val lastName: String,

    @ColumnInfo(name = "position")
    @SerializedName("position")
    val position: String,

    @ColumnInfo(name = "age")
    @SerializedName("age")
    val age: Int,

    @ColumnInfo(name = "birth_date")
    @SerializedName("birth_date")
    val birthDate: String,

    @ColumnInfo(name = "nationality")
    @SerializedName("nationality")
    var nationality: String,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: String,

    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    val weight: String,

    @ColumnInfo(name = "goals")
    @SerializedName("goals")
    val goals: Goals,

    @ColumnInfo(name = "cards")
    @SerializedName("cards")
    val cards: Cards,

    @ColumnInfo(name = "games")
    @SerializedName("games")
    val games: Games
)