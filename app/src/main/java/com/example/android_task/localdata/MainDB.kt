package com.example.android_task.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_task.model.FilmEntity

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDBConverter::class)
abstract class MainDB : RoomDatabase(){

    abstract fun getFilmDao(): FilmDao


    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDatabase(context: Context): MainDB {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}