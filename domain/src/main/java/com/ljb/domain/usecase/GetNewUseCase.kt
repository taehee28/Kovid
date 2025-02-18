package com.ljb.domain.usecase

import com.ljb.domain.NetworkState
import com.ljb.domain.entity.News
import com.ljb.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow

class GetNewUseCase constructor(private val repository: NewRepository){
    operator fun invoke(query: String) : Flow<NetworkState<List<News>>> = repository.getNewsData(query)
}