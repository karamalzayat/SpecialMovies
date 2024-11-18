package com.example.specialmovies.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.data.remote.retrofit.WebServices
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val remoteDataSource: WebServices,
) : PagingSource<Int, Movie>() {
    private val apiKey: String = "b8d7f72abee18fd93012e158e9211297"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = remoteDataSource.getMovies(
                page = currentPage,
                apiKey = apiKey
            )
            LoadResult.Page(
                data = movies.body()!!.result,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.body()!!.result.isEmpty()) null else movies.body()!!.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}