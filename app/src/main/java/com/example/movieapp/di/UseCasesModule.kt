package com.example.movieapp.di

import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.MoviesUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUsesCases {
        return MoviesUsesCases(
            getMovies = GetMoviesUseCase(moviesRepository),
        )
    }
}

