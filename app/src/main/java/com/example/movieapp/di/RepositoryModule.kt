package com.example.movieapp.di

import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.source.remote.MovieService
import com.example.movieapp.domain.repository.MoviesRepository
import dagger.Provides
import javax.inject.Singleton

object RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesService: MovieService,
    ): MoviesRepository = MovieRepositoryImpl(moviesService)
}