package com.example.movieapp.di

import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.MoviesUsesCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUsesCases {
        return MoviesUsesCases(
            getMovies = GetMoviesUseCase(moviesRepository)
        )
    }
}

