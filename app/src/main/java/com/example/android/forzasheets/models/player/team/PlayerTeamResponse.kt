package com.example.android.forzasheets.models.player.team

import com.google.gson.annotations.SerializedName

/**
 * A PlayerTeam-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [playersTeam] the list of general player info retrieved from the API
 */
class PlayerTeamResponse(
    @SerializedName("players") val playersTeam: List<PlayerTeam>
)