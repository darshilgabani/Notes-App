package com.biz.notesapp.database

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biz.notesapp.dao.NotesDao
import com.biz.notesapp.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes Database")
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }

        }

    }
}