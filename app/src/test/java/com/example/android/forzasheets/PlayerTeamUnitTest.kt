package com.example.android.forzasheets

import com.example.android.forzasheets.models.player.team.PlayerTeam
import org.junit.Assert
import org.junit.Test

class PlayerTeamUnitTest {
    @Test
    fun playerTeam_isCreated() {
        val player = PlayerTeam(1, "Ronaldo", "Attacker", 7, 35, "Portugal")

        Assert.assertEquals("Ronaldo", player.playerName)
    }
}