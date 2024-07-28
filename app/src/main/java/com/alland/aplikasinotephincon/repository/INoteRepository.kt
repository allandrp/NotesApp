package com.alland.aplikasinotephincon.repository

import androidx.lifecycle.LiveData
import com.alland.aplikasinotephincon.room.NoteEntity

interface INoteRepository {
    fun getAllNote(): LiveData<List<NoteEntity>>
    suspend fun insertNote(note: NoteEntity): Long
    suspend fun deleteNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    fun getNoteById(id: Long): LiveData<NoteEntity>
}