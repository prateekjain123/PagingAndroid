package com.pn.appentustest.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pn.appentustest.api.ApiService
import com.pn.appentustest.data.ImagesData
import kotlinx.coroutines.delay


class PostsPagingSource(private val apiService: ApiService) :
    PagingSource<Int, ImagesData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesData> {
        val position = params.key ?: 1

        return try {
            val response = apiService.fetchData(position)
            LoadResult.Page(
                response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImagesData>): Int? {
        return state.anchorPosition
    }

}