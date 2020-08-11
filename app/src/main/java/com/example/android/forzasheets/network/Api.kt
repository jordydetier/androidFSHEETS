package com.example.android.forzasheets.network

import com.example.android.forzasheets.models.player.detail.GetPlayerDetailResponse
import com.example.android.forzasheets.models.player.team.GetPlayerTeamResponse
import com.example.android.forzasheets.models.standing.GetStandingsResponse
import com.example.android.forzasheets.models.team.GetTeamResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Base URL used to connect to the API
 */
private const val BASE_URL =
    "https://v2.api-football.com/"

/**
 * API key used to connect to the API
 */

private const val AUTH_TOKEN = "1ab22aab615c428e280d25f31d9f4e68"

/**
 * Instance of retrofit, which handles the connection to the API
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * This object allows other classes to access the retrofitService
 */
object FootballApi {
    val retrofitService: Api by lazy {
        retrofit.create(Api::class.java)
    }
}

/**
 * This interface handles the calls to the online API
 */
interface Api {

    /**
     * Using a unique [apiKey], this method retrieves all details from a team with a specific [teamId]
     *
     */
    @GET("teams/team/{team_id}")
    fun getTeamDetails(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("team_id") teamId: String
    ): Call<GetTeamResponse>

    /**
     * Using a unique [apiKey], this method retrieves all players from a team with a specific [teamId]
     *
     */
    @GET("players/squad/{team_id}/2019-2020")
    fun getAllPlayersFromTeam(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("team_id") teamId: String
    ): Call<GetPlayerTeamResponse>

    /**
     * Using a unique [apiKey], this method retrieves all details from a player with a specific [playerId]
     *
     */
    @GET("players/player/{player_id}/2019-2020")
    fun getPlayerDetails(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("player_id") playerId: String
    ): Call<GetPlayerDetailResponse>

    /**
     * Using a unique [apiKey], this method retrieves the standings from a league with a specific [leagueId]
     *
     */
    @GET("leagueTable/{league_id}")
    fun getStandings(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("league_id") leagueId: String
    ): Call<GetStandingsResponse>


}