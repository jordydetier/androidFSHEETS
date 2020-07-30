package com.example.android.forzasheets.models.player.detail

import com.google.gson.annotations.SerializedName

class PlayerDetailResponse (
    @SerializedName("players") val playersDetail: List<PlayerDetail>
)