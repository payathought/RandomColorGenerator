package com.example.randomcolorgenerator.interfaces

import android.graphics.ColorSpace
import android.provider.CalendarContract
import com.example.randomcolorgenerator.model.ColorSchemeModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface APIClient
{
    @GET("/json/schemes/random/{schemeCount}")
    fun getResult(
        @Path("schemeCount") schemeCount: Int? = 1,
        @Query("scheme_size_limit") limit: Int? = 9
    ): Call<ColorSchemeModel>




}