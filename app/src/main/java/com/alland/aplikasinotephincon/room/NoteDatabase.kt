package com.alland.aplikasinotephincon.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun dao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        NoteDatabase::class.java,
                        "NoteDatabase"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
            }

            return INSTANCE as NoteDatabase
        }
    }
}