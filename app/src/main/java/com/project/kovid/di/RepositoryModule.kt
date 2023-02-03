package com.project.kovid.di

import com.ljb.data.repository.AreaRepositoryImpl
import com.ljb.data.repository.ChartRepositoryImpl
import com.ljb.data.repository.remote.datasourceimpl.AreaDataSourceImpl
import com.ljb.data.repository.remote.datasourceimpl.ChartDataSourceImpl
import com.ljb.domain.repository.AreaRepository
import com.ljb.domain.repository.ChartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Hilt로 Repository관련 의존성 주입을 해주기 위한 모듈
 * */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideChartRepository(
        chartRemoteDataSourceImpl: ChartDataSourceImpl
    ): ChartRepository{
        return ChartRepositoryImpl(
            chartRemoteDataSourceImpl
        )
    }

    @Provides
    @Singleton
    fun provideAreaRepository(
        areaRemoteDataSourceImpl: AreaDataSourceImpl
    ): AreaRepository{
        return AreaRepositoryImpl(
            areaRemoteDataSourceImpl
        )
    }
}