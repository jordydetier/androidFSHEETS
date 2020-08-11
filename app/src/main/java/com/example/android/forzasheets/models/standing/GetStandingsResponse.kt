package com.example.android.forzasheets.models.standing

import com.google.gson.annotations.SerializedName

/**
 * A Standings-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [response] the general object retrieved from the API
 */
data class GetStandingsResponse(
    @SerializedName("api") val response: StandingsResponse
)