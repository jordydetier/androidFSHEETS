package com.example.android.forzasheets.models.team

import com.google.gson.annotations.SerializedName

data class TeamResponse (
    @SerializedName("results") val total : Int,
    @SerializedName("teams") val teams: List<Team>
)