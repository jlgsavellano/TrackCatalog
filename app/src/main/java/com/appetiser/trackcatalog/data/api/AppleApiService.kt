package com.appetiser.trackcatalog.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Declaration of API endpoints
 * Using Retrofit for consuming REST APIs
 */
interface AppleApiService {

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("limit") limit: Int = 300
    ): Response<TrackResponse>
}