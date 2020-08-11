package com.example.android.forzasheets.models.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the retrieved team
 *
 * This class is used to represent the team retrieved from the API
 *
 * @param [id] unique id to represent each team object
 * @param [name] name of the team
 * @param [logo] imageURL for the team logo
 * @param [country] country where the team is located
 * @param [founded] year the team was founded
 * @param [venueName] name of the teams venue
 * @param [venueCity] name of the city the venue is located
 * @param [venueCapacity] maximum capacity of the venue
 */
@Entity(tableName = "team")
data class Team(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "team_id")
    @SerializedName("team_id")
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "logo")
    @SerializedName("logo")
    val logo: String,

    @ColumnInfo(name = "country")
    @SerializedName("country")
    val country: String,

    @ColumnInfo(name = "founded")
    @SerializedName("founded")
    val founded: Int,

    @ColumnInfo(name = "venue_name")
    @SerializedName("venue_name")
    val venueName: String,

    @ColumnInfo(name = "venue_city")
    @SerializedName("venue_city")
    val venueCity: String,

    @ColumnInfo(name = "venue_capacity")
    @SerializedName("venue_capacity")
    val venueCapacity: Int
)