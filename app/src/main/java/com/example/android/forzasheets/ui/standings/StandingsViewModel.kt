package com.example.android.forzasheets.ui.standings

import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.repository.ForzaSheetsRepository


class StandingsViewModel: ViewModel() {
    private var forzaSheetsRepository = ForzaSheetsRepository()

    fun getStandings(
        leagueId: String,
        onSuccess: (standings: List<Standings>) -> Unit,
        onError: () -> Unit
    ) {
        forzaSheetsRepository.getStandings(
            leagueId,
            onSuccess = { standings ->
                onSuccess.invoke(standings)
            },
            onError = {
                onError.invoke()
            }
        )
    }

}