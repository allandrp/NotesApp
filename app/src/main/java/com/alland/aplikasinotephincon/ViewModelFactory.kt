package com.alland.aplikasinotephincon

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alland.aplikasinotephincon.form.NoteFormViewModel
import com.alland.aplikasinotephincon.main.MainViewModel
import com.alland.aplikasinotephincon.repository.INoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val noteRepository: INoteRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(noteRepository) as T
            }

            modelClass.isAssignableFrom(NoteFormViewModel::class.java) -> {
                return NoteFormViewModel(noteRepository) as T
            }

            else -> {
                throw IllegalArgumentException("Unknown View Model Class : " + modelClass.name)
            }
        }
    }
}