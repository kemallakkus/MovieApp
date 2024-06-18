package com.example.movieapp.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.util.Resource
import com.example.movieapp.util.toPagingException
import kotlinx.coroutines.flow.Flow

class MoviesPagingSource<Value : Any>(
    val loadDataFromApi: suspend (page: Int, pageSize: Int) -> List<Value>?,
) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = loadDataFromApi(currentPage, params.loadSize)
            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (data.isNullOrEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val LIMIT = 30
    }
}

fun <T : Any> safeApiCallPaging(
    loadDataFromApi: suspend (page: Int, pageSize: Int) -> Resource<List<T>>
): Flow<PagingData<T>> {
    return setPager(
        pagingSourceFactory = {
            MoviesPagingSource { page, pageSize ->
                when (val result = loadDataFromApi(page, pageSize)) {
                    is Resource.Success -> result.data
                    is Resource.Error -> throw result.toPagingException()
                }
            }
        }
    ).flow
}

internal fun <Value : Any> setPager(
    pageSize: Int = MoviesPagingSource.LIMIT,
    initialLoadSize: Int = MoviesPagingSource.LIMIT,
    enablePlaceholders: Boolean = false,
    pagingSourceFactory: () -> MoviesPagingSource<Value>,
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