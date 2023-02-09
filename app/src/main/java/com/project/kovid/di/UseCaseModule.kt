package com.project.kovid.di

import com.ljb.domain.repository.CovidRepository
import com.ljb.domain.repository.HospitalRepository
import com.ljb.domain.usecase.GetAreaListUseCase
import com.ljb.domain.usecase.GetChartListUseCase
import com.ljb.domain.usecase.GetSelectiveClinicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Hilt로 UseCase관련 의존성 주입을 해주기 위한 모듈
 * */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetChartUseCase(repository: CovidRepository) = GetChartListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAreaUseCase(repository: CovidRepository) = GetAreaListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSelectiveClinicUseCase(repository: HospitalRepository) = GetSelectiveClinicUseCase(repository)
}