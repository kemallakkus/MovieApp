package com.example.movieapp.di

import android.app.Application
import com.example.movieapp.data.manger.LocalUserMangerImpl
import com.example.movieapp.domain.manger.LocalUserManger
import com.example.movieapp.domain.usecases.AppEntryUseCases
import com.example.movieapp.domain.usecases.ReadAppEntry
import com.example.movieapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppEntryModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application,
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}
