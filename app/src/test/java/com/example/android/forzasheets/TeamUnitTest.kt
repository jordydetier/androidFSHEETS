package com.example.android.forzasheets

import com.example.android.forzasheets.models.team.Team
import org.junit.Assert
import org.junit.Test

class TeamUnitTest {
    @Test
    fun team_isCreated() {
        val team = Team(1, "RSC Anderlecht", "", "Belgium", 1906, "Lottopark", "Anderlecht", 21500)

        Assert.assertEquals("RSC Anderlecht", team.name)
    }
}