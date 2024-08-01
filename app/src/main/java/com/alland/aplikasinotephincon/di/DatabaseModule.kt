package com.alland.aplikasinotephincon.di

import android.content.Context
import androidx.room.Room
import com.alland.aplikasinotephincon.room.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "Note.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDao(database: NoteDatabase) = database.dao()
}