package com.example.android.forzasheets.models.Standings

import com.google.gson.annotations.SerializedName

data class StandingsResponse (
    @SerializedName("results") val total : Int,
    @SerializedName("standings") val standings: List<List<Standings>>
)