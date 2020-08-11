package com.example.android.forzasheets.ui.playerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.models.player.detail.PlayerDetail
import com.example.android.forzasheets.repository.ForzaSheetsApiRepository

/**
 * PlayerDetails' ViewModel
 *
 * This ViewModel contains the logic behind the MovieDetails fragment
 *
 * @param [playerId] The id of selected player from the player list
 *
 */
class PlayerDetailsViewModel(
    playerId: String
) : ViewModel() {

    /**
     * Repository to retrieve movies from the online database
     */
    private var forzaSheetsRepository = ForzaSheetsApiRepository()

    /**
     * Encapsulated id of the selected player
     *
     */
    private val _playerId = MutableLiveData<String>()

    /**
     * Encapsulated PlayerDetail object of the selected player
     *
     */
    private var _player = MutableLiveData<PlayerDetail>()

    /**
     * Value of the id of the selected player
     */
    val selectedPlayerId: LiveData<String>
        get() = _playerId

    /**
     * Gets called during the creation of the viewmodel, allocates the passed player id to the property
     */
    init {
        _playerId.value = playerId
    }

    /**
     * Delegates the call from the view to the repository
     */
    fun getPlayerDetails(
        playerId: String,
        onSuccess: (player: PlayerDetail) -> Unit,
        onError: () -> Unit
    ) {
        forzaSheetsRepository.getPlayerDetails(
            playerId,
            onSuccess = { player ->
                onSuccess.invoke(player)
                _player.value = player
            },
            onError = {
                onError.invoke()
            }
        )
    }

}