package com.example.randomcolorgenerator.room_local_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ColorGeneratorEntities::class], version = 1)
abstract class ColorGeneratorDatabase : RoomDatabase() {


    abstract fun colorGenDao(): ColorGeneratorDAO


    companion object {
        @Volatile private var instance: ColorGeneratorDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            ColorGeneratorDatabase::class.java, "DBColorScheme.db")
            .allowMainThreadQueries()
            .build()


    }

}