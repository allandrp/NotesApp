package com.alland.aplikasinotephincon.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alland.aplikasinotephincon.repository.INoteRepository
import com.alland.aplikasinotephincon.room.NoteEntity

class MainViewModel(private val noteRepository: INoteRepository): ViewModel() {
    fun getAllNote(): LiveData<List<NoteEntity>> = noteRepository.getAllNote()
}