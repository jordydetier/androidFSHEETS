package com.example.android.forzasheets.models.player.detail

import com.google.gson.annotations.SerializedName

/**
 * A PlayerDetail-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [playersDetail] the list of detailed player info retrieved from the API
 */
class PlayerDetailResponse(
    @SerializedName("players") val playersDetail: List<PlayerDetail>
)