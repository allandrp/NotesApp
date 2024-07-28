package com.alland.aplikasinotephincon.di

import android.content.Context
import com.alland.aplikasinotephincon.repository.INoteRepository
import com.alland.aplikasinotephincon.repository.NoteRepository
import com.alland.aplikasinotephincon.room.NoteDatabase

object Injection {
    fun provideRepository(context: Context): INoteRepository{
        val db = NoteDatabase.getInstance(context)
        val repository = NoteRepository.getInstance(db.dao())

        return repository
    }
}