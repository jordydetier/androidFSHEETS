package com.example.android.forzasheets

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.forzasheets.database.ForzaSheetsDatabase
import com.example.android.forzasheets.database.ForzaSheetsDatabaseDao
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.models.Standings.StandingsMatches
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@RunWith(AndroidJUnit4::class)
class ForzaSheetsDatabaseTest {
    private lateinit var forzaSheetsDao: ForzaSheetsDatabaseDao
    private lateinit var db: ForzaSheetsDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ForzaSheetsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        forzaSheetsDao = db.forzaSheetsDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTeams() {
        val matchesInfo = StandingsMatches(1,1,1,1,1,1,1)
        val standings = Standings(5,4, "test", "test","e",5, matchesInfo , 5)
        forzaSheetsDao.insert(standings)
        assertEquals(forzaSheetsDao.getAllStandings().contains(standings), true)
    }

    @Test
    @Throws(Exception::class)
    fun insertAllAndGetTeams() {
        val matchesInfo = StandingsMatches(1,1,1,1,1,1,1)
        val standings = Standings(5,4, "test", "test","e",5, matchesInfo , 5)
        val list = emptyList<Standings>()
        list.toMutableList().add(standings)
        forzaSheetsDao.insertAll(list)
        Log.d("inserted", "inserted all");


        assertEquals(forzaSheetsDao.getAllStandings().contains(standings), true)
    }
}
