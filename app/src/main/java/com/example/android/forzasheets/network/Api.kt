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

private const val BASE_URL =
    "https://v2.api-football.com/"

private const val AUTH_TOKEN = "1ab22aab615c428e280d25f31d9f4e68"
private val loggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

object FootballApi {
    val retrofitService: Api by lazy {
        retrofit.create(Api::class.java)
    }
}

interface Api {
    @GET("teams/team/{team_id}")
    fun getTeamDetails(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("team_id") teamId: String
    ): Call<GetTeamResponse>

    @GET("players/squad/{team_id}/2019-2020")
    fun getAllPlayersFromTeam(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("team_id") teamId: String
    ): Call<GetPlayerTeamResponse>

    @GET("players/player/{player_id}/2019-2020")
    fun getPlayerDetails(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("player_id") playerId: String
    ): Call<GetPlayerDetailResponse>


    @GET("leagueTable/{league_id}")
    fun getStandings(
        @Header("X-RapidAPI-Key") apiKey: String = AUTH_TOKEN,
        @Path("league_id") leagueId: String
    ): Call<GetStandingsResponse>


}