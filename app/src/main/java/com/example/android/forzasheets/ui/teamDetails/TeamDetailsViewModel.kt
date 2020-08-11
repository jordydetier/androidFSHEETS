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

    /**
     * Repository to retrieve movies from the online database
     */
    private var forzaSheetsRepository = ForzaSheetsApiRepository()

    /**
     * Encapsulated id of the selected team
     *
     */
    private val _teamId = MutableLiveData<String>()

    /**
     * Value of the id of the selected team
     */
    val selectedTeamId: LiveData<String>
        get() = _teamId

    /**
     * Gets called during the creation of the viewmodel, allocates the passed team id to the property
     */
    init {
        _teamId.value = teamId
    }

    /**
     * Delegates the call from the view to the repository
     */
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

    /**
     * Delegates the call from the view to the repository
     */
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