package com.alland.aplikasinotephincon.di

import com.alland.aplikasinotephincon.repository.INoteRepository
import com.alland.aplikasinotephincon.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(noteRepository: NoteRepository): INoteRepository

}