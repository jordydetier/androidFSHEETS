package com.example.android.forzasheets.models.player.team

import com.google.gson.annotations.SerializedName

class PlayerTeamResponse(
    @SerializedName("players") val playersTeam: List<PlayerTeam>
)