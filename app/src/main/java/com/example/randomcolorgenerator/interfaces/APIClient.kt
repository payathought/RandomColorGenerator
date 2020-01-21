package com.example.randomcolorgenerator.interfaces

import com.example.randomcolorgenerator.model.ColorSchemeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient
{
    @GET("/json/schemes/random/{schemeCount}")
    fun getResult(
        @Path("schemeCount") schemeCount: Int? = 1,
        @Query("scheme_size_limit") limit: Int? = 9
    ): Call<ColorSchemeModel>




}