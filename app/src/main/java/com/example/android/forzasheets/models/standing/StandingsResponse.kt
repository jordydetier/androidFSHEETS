package com.example.android.forzasheets.models.standing

import com.google.gson.annotations.SerializedName

/**
 * A Standings-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [total] the amount of teams retrieved from the API
 * @param [standings] the list of standings retrieved from the API
 */
data class StandingsResponse(
    @SerializedName("results") val total: Int,
    @SerializedName("standings") val standings: List<List<Standings>>
)