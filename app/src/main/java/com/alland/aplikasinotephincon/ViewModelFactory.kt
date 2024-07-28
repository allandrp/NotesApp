package com.alland.aplikasinotephincon

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alland.aplikasinotephincon.di.Injection
import com.alland.aplikasinotephincon.form.NoteFormViewModel
import com.alland.aplikasinotephincon.main.MainViewModel
import com.alland.aplikasinotephincon.repository.INoteRepository

class ViewModelFactory private constructor(private val noteRepository: INoteRepository) :
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

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    val instance = ViewModelFactory(Injection.provideRepository(context))
                    INSTANCE = instance
                    return instance
                }
            }

            return INSTANCE as ViewModelFactory
        }
    }
}