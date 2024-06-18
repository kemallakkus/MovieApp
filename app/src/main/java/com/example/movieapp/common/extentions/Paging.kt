package com.example.movieapp.common.extentions

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.common.base.BasePagingSource
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

suspend fun <T : Any> safeApiCallPaging(
    loadDataFromApi: suspend (page: Int, pageSize: Int) -> Resource<List<T>>
): Flow<PagingData<T>> {
    return setPager(
        pagingSourceFactory = {
            BasePagingSource { page, pageSize ->
                when (val result = loadDataFromApi(page, pageSize)) {
                    is Resource.Success -> result.data
                    is Resource.Fail -> throw Exception("API call failed: ${result.message}")
                }
            }
        }
    ).flow
}

fun <Value : Any> setPager(
    pageSize: Int = BasePagingSource.LIMIT,
    initialLoadSize: Int = BasePagingSource.LIMIT,
    enablePlaceholders: Boolean = false,
    pagingSourceFactory: () -> BasePagingSource<Value>,
): Pager<Int, Value> {
    return Pager(
        config = PagingConfig(
            pageSize = pageSize,
            initialLoadSize = initialLoadSize,
            prefetchDistance = 40,
            enablePlaceholders = enablePlaceholders,
        ),
        pagingSourceFactory = pagingSourceFactory
    )
}