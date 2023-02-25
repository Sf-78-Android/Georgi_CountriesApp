package com.example.countryinfoapp.modules

import com.example.countryinfoapp.repositories.CountryRepositoryImplementation
import com.example.countryinfoapp.repositories.ICountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: CountryRepositoryImplementation): ICountryRepository
}