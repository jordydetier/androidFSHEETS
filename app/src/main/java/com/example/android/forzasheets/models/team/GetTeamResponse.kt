package com.example.android.forzasheets.models.team

import com.google.gson.annotations.SerializedName

data class GetTeamResponse(
    @SerializedName("api") val response: TeamResponse
)