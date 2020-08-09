package com.example.android.forzasheets.ui.playerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.forzasheets.models.player.detail.PlayerDetail
import com.example.android.forzasheets.repository.ForzaSheetsApiRepository

class PlayerDetailsViewModel(
    playerId: String
) : ViewModel() {
    private var forzaSheetsRepository = ForzaSheetsApiRepository()
    private val _playerId = MutableLiveData<String>()
    private var _player = MutableLiveData<PlayerDetail>()

    val selectedPlayerId: LiveData<String>
        get() = _playerId

    init {
        _playerId.value = playerId
    }

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