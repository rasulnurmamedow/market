package com.example.recyclerview

import android.content.Context
import android.content.SharedPreferences
import com.example.recyclerview.interfaces.ApiInterface
import com.example.recyclerview.interfaces.RetrofitInterface
import com.example.recyclerview.repositoryes.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return RetrofitInterface.retrofit.create(ApiInterface::class.java)
    }



    @Provides
    @Singleton
    fun provideHomeRepository(api: ApiInterface): Repository {
        return Repository(api)
    }



}