package com.example.android.forzasheets.models.standing

import com.google.gson.annotations.SerializedName

data class GetStandingsResponse(
    @SerializedName("api") val response: StandingsResponse
)