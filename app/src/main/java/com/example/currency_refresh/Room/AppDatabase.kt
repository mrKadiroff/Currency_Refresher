package com.example.currency_refresh.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currency_refresh.models.Currency

@Database(entities = [Currency::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun valyutaDao(): ValyutaDao
    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"valyuta.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}