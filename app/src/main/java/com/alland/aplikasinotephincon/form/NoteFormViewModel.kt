package com.alland.aplikasinotephincon.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alland.aplikasinotephincon.repository.INoteRepository
import com.alland.aplikasinotephincon.room.NoteEntity
import kotlinx.coroutines.launch

class NoteFormViewModel(private val repository: INoteRepository) : ViewModel() {

    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun getNoteById(id: Long): LiveData<NoteEntity> {
        return repository.getNoteById(id)
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}