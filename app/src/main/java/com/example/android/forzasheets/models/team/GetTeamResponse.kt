package com.example.android.forzasheets.models.team

import com.google.gson.annotations.SerializedName

/**
 * A Team-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [response] the general object retrieved from the API
 */
data class GetTeamResponse(
    @SerializedName("api") val response: TeamResponse
)