package com.example.android.forzasheets.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.forzasheets.models.Standings.Standings
import com.example.android.forzasheets.models.Standings.StandingsMatches


@Database(entities = [Standings::class], version = 7, exportSchema = false)
abstract class ForzaSheetsDatabase : RoomDatabase() {

    abstract val forzaSheetsDatabaseDao: ForzaSheetsDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: ForzaSheetsDatabase? = null
        fun getInstance(context: Context): ForzaSheetsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ForzaSheetsDatabase::class.java,
                        "forza_sheets_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}

