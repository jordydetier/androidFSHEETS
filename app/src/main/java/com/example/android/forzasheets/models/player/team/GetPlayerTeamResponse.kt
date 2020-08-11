package com.example.android.forzasheets.models.player.team

import com.google.gson.annotations.SerializedName

/**
 * A PlayerTeam-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [response] the general object retrieved from the API
 */
class GetPlayerTeamResponse(
    @SerializedName("api") val response: PlayerTeamResponse
)