package com.pn.appentustest.api


import com.pn.appentustest.data.ImagesData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun fetchData(@Query("page") position: Int): List<ImagesData>
}