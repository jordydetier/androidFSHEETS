package com.example.android.forzasheets.models.standing

import com.google.gson.annotations.SerializedName

data class StandingsResponse(
    @SerializedName("results") val total: Int,
    @SerializedName("standings") val standings: List<List<Standings>>
)