package com.example.android.forzasheets.models.player.detail

import com.google.gson.annotations.SerializedName

/**
 * A PlayerDetail-helper class
 *
 * This class is used as a helper class for retrieving data from the API
 *
 * @param [response] the general object retrieved from the API
 */
class GetPlayerDetailResponse(
    @SerializedName("api") val response: PlayerDetailResponse
)