package com.appetiser.trackcatalog.data.remote.api

import com.appetiser.trackcatalog.data.remote.response.TrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Declaration of API endpoints
 * Using Retrofit for consuming REST APIs
 */
interface ApiService {

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("limit") limit: Int = 300
    ): Response<TrackResponse>
}

/**
 * Added a safety check when exception is encountered
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                Result.Success(body)
            } ?: run {
                Result.Error(Exception("response body is null"))
            }
        } else {
            Result.Error(Exception("${response.code()} ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val e: Exception? = null) : Result<Nothing>()
}