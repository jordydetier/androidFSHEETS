package com.example.android.forzasheets.ui.teamDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.models.player.team.PlayerTeam
import com.example.android.forzasheets.models.team.Team
import com.example.android.forzasheets.repository.ForzaSheetsApiRepository

class TeamDetailsViewModel(
    teamId: String
) : ViewModel() {
    private var forzaSheetsRepository = ForzaSheetsApiRepository()


    private val _teamId = MutableLiveData<String>()

    /**
     * Value of the selected movie
     */
    val selectedTeamId: LiveData<String>
        get() = _teamId

    init {
        _teamId.value = teamId
    }

    fun getTeamDetails(
        teamId: String,
        onSuccess: (team: Team) -> Unit,
        onError: () -> Unit
    ) {
        forzaSheetsRepository.getTeamDetails(
            teamId,
            onSuccess = { team ->
                onSuccess.invoke(team)
            },
            onError = {
                onError.invoke()
            }
        )
    }

    fun getAllPlayersFromTeam(
        teamId: String,
        onSuccess: (players: List<PlayerTeam>) -> Unit,
        onError: () -> Unit
    ) {
        forzaSheetsRepository.getAllPlayersFromTeam(
            teamId,
            onSuccess = { players ->
                onSuccess.invoke(players)
            },
            onError = {
                onError.invoke()
            }
        )
    }

}