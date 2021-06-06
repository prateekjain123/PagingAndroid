package com.pn.appentustest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.pn.appentustest.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImagesRepository @Inject constructor(private val apiService: ApiService) {
    fun getResults() =
        Pager(
            config = PagingConfig(maxSize = 100, pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = {
                PostsPagingSource(apiService)
            }).flow
}
