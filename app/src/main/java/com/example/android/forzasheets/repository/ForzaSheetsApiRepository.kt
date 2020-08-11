package com.example.android.forzasheets.repository

import com.example.android.forzasheets.models.player.detail.GetPlayerDetailResponse
import com.example.android.forzasheets.models.player.detail.PlayerDetail
import com.example.android.forzasheets.models.player.team.GetPlayerTeamResponse
import com.example.android.forzasheets.models.player.team.PlayerTeam
import com.example.android.forzasheets.models.team.GetTeamResponse
import com.example.android.forzasheets.models.team.Team
import com.example.android.forzasheets.network.FootballApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A repository.
 *
 * This class fetches data from an API using the API.kt file.
 */

class ForzaSheetsApiRepository {

    /**
     * Fetches details from a team with a certain [teamId] and activates an [onSuccess] or [onError] invocation.
     */
    fun getTeamDetails(
        teamId: String,
        onSuccess: (team: Team) -> Unit,
        onError: () -> Unit
    ) {
        FootballApi.retrofitService.getTeamDetails(teamId = teamId)
            .enqueue(object : Callback<GetTeamResponse> {
                override fun onResponse(
                    call: Call<GetTeamResponse>,
                    response: Response<GetTeamResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.response.teams.first())
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTeamResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    /**
     * Fetches all players from a team with a certain [teamId] and activates an [onSuccess] or [onError] invocation.
     */
    fun getAllPlayersFromTeam(
        teamId: String,
        onSuccess: (players: List<PlayerTeam>) -> Unit,
        onError: () -> Unit
    ) {
        FootballApi.retrofitService.getAllPlayersFromTeam(teamId = teamId)
            .enqueue(object : Callback<GetPlayerTeamResponse> {
                override fun onResponse(
                    call: Call<GetPlayerTeamResponse>,
                    response: Response<GetPlayerTeamResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.response.playersTeam)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetPlayerTeamResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    /**
     * Fetches details from a player with a certain [playerId] and activates an [onSuccess] or [onError] invocation.
     */
    fun getPlayerDetails(
        playerId: String,
        onSuccess: (playerDetail: PlayerDetail) -> Unit,
        onError: () -> Unit
    ) {
        FootballApi.retrofitService.getPlayerDetails(playerId = playerId)
            .enqueue(object : Callback<GetPlayerDetailResponse> {
                override fun onResponse(
                    call: Call<GetPlayerDetailResponse>,
                    response: Response<GetPlayerDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.response.playersDetail.first())
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetPlayerDetailResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


}