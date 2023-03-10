package com.example.countryinfoapp.modules

import com.example.countryinfoapp.networking.ApolloApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideWebService() = ApolloApi()

}