package com.example.movieapp.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.dto.ResultDto

class MoviesPagingSource(
    private val moviesApi: MovieService,
    private val sources: String
): PagingSource<Int, ResultDto>() {

    private var totalMoviesCount = 0

    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        val page = params.key ?: 1

        return try {
            val moviesResponse = moviesApi.getMovies(page = page, sources = sources)
            val movieCount = moviesResponse.results?.size ?: 0
            totalMoviesCount += movieCount

            val movies = moviesResponse.results?.distinctBy { it.title }

            LoadResult.Page(
                data = movies.orEmpty(),
                nextKey = if (totalMoviesCount == moviesResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }
        catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}