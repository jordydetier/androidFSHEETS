package com.example.android.forzasheets

import com.example.android.forzasheets.models.standing.Standings
import com.example.android.forzasheets.models.standing.StandingsMatches
import org.junit.Assert
import org.junit.Test

class StandingsUnitTest {
    @Test
    fun standing_isCreatmed() {
        val matchInfo = StandingsMatches(15, 50, 50, 0, 0, 90, 0)
        val standing = Standings(1, 15, "RSC Anderlecht", "", "", 90, matchInfo, 15, "555")

        Assert.assertEquals("RSC Anderlecht", standing.name)
    }
}