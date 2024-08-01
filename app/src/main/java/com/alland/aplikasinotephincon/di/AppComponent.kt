package com.alland.aplikasinotephincon.di

import android.content.Context
import com.alland.aplikasinotephincon.form.NoteFormActivity
import com.alland.aplikasinotephincon.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(noteFormActivity: NoteFormActivity)
}