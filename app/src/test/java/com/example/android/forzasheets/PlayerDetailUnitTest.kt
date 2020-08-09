package com.example.android.forzasheets

import com.example.android.forzasheets.models.player.detail.Cards
import com.example.android.forzasheets.models.player.detail.Games
import com.example.android.forzasheets.models.player.detail.Goals
import com.example.android.forzasheets.models.player.detail.PlayerDetail
import org.junit.Assert
import org.junit.Test

class PlayerDetailUnitTest {
    @Test
    fun playerDetail_isCreated() {
        val goals = Goals(30, 10, 0, 0)
        val cards = Cards(10, 10)
        val games = Games(50, 5000)
        val player = PlayerDetail(
            1,
            "Cristiano",
            "Ronaldo",
            "Attacker",
            35,
            "5/02/1985",
            "Portugal",
            "189cm",
            "84kg",
            goals,
            cards,
            games
        )
        Assert.assertEquals("Ronaldo", player.lastName)
    }
}