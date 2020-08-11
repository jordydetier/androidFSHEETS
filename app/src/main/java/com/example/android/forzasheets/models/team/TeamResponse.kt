package com.example.android.forzasheets.models.team

import com.google.gson.annotations.SerializedName

/**
 * A Team-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [total] the amount of teams retrieved from the API
 * @param [teams] the list of teams retrieved from the API
 */
data class TeamResponse(
    @SerializedName("results") val total: Int,
    @SerializedName("teams") val teams: List<Team>
)