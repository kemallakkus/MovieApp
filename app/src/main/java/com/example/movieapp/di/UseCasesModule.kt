package com.example.movieapp.di

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetDetailUseCase
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.MoviesUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {
    @Provides
    @ViewModelScoped
    fun provideMoviesUseCases(
        moviesRepository: MovieRepository
    ): MoviesUsesCases {
        return MoviesUsesCases(
            getMovies = GetMoviesUseCase(moviesRepository),
            getDetail = GetDetailUseCase(moviesRepository)
        )
    }
}