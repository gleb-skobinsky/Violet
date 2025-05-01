package com.violet.features.notes

import com.violet.features.notes.repository.NotesRepository
import com.violet.features.notes.repository.NotesRepositoryImpl
import org.koin.dsl.module

val notesModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(get())
    }
}