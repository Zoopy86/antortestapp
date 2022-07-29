package com.zinc.antortestapp.data.di

import com.zinc.antortestapp.data.repository.EntryRepositoryImpl
import com.zinc.antortestapp.data.room.EntryDao
import com.zinc.antortestapp.domain.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object EntryListModule {

    @Provides
    fun provideEntryRepository(entryDao: EntryDao): EntryRepository = EntryRepositoryImpl(entryDao)

}