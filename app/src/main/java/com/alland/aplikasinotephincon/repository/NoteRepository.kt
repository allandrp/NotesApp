package com.alland.aplikasinotephincon.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alland.aplikasinotephincon.room.NoteDao
import com.alland.aplikasinotephincon.room.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val dao: NoteDao): INoteRepository {
    override fun getAllNote(): LiveData<List<NoteEntity>> {
        return dao.getAllNotes()
    }

    override suspend fun insertNote(note: NoteEntity): Long {
        return withContext(Dispatchers.IO){
            dao.insertNote(note)
        }
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return withContext(Dispatchers.IO){
            dao.deleteNote(note)
        }
    }

    override suspend fun updateNote(note: NoteEntity) {
        return withContext(Dispatchers.IO){
            dao.updateNote(note)
        }
    }

    override fun getNoteById(id: Long): LiveData<NoteEntity> {
        return dao.getNoteById(id)
    }
}