package com.zinc.antortestapp.data.di

import android.content.Context
import androidx.room.Room
import com.zinc.antortestapp.data.room.EntryDao
import com.zinc.antortestapp.data.room.EntryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideEntryDb(@ApplicationContext context: Context): EntryDatabase {
        return Room
            .databaseBuilder(context, EntryDatabase::class.java, EntryDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEntryDao(entryDatabase: EntryDatabase): EntryDao = entryDatabase.entryDao()

}