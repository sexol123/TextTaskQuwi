package com.example.testtask.data

import com.example.testtask.data.request.LoginRequest
import com.example.testtask.data.request.UpdateRequest
import com.example.testtask.data.response.LoginResponse
import com.example.testtask.data.response.Project
import com.example.testtask.data.response.ProjectResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface QuwiApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("projects")
    suspend fun getAllProjects(): ProjectResponse

    @FormUrlEncoded
    @POST("projects-manage/update")
    suspend fun update(
        @Query("id") id: Long,
        @FieldMap params: Map<String, String> = mapOf()
    ): Project
}