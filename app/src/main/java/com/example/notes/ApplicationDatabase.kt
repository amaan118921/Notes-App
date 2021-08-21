package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.model.Item

@Database(entities = [Item::class], version = 2, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun itemDao(): Dao

    companion object {
        var INSTANCE: ApplicationDatabase? = null
        fun getDatabase(context: Context): ApplicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                context.applicationContext, ApplicationDatabase::class.java,"item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance


            }
        }
    }

}