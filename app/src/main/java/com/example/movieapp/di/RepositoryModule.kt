package com.example.movieapp.di

import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

//Provides amacı elle provides etmek. Ek yük olur. Compiler time da zaman harcıyo provideları yapmak için. Hiltin otomatiik yapması için binds. Binds run time da hallediyo.  Birden fazla impll ı interface işaret ederse üstlerinde anatosyon kullanman lazım
//repository vm ye hizmet ediyo gibi. vm nin yaşam döngüsüne bağlamak daha iyi. singlton yapınca app in yaşam döngüsüne bağlı.
//Installın genel kapsamı belirtir. Viewmodel üstünde başka kapsam kullanmıcam.

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMoviesRepository(
        impl: MovieRepositoryImpl,
    ): MovieRepository
}