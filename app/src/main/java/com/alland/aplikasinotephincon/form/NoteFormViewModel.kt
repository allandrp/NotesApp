package com.alland.aplikasinotephincon.form

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
}