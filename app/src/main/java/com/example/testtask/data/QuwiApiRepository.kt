package com.example.testtask.data

import com.example.testtask.data.request.LoginRequest
import com.example.testtask.data.response.Project
import com.example.testtask.data.response.ProjectResponse

class QuwiApiRepository(private val quwiApi: QuwiApi) {

    suspend fun login(email: String, password: String) = quwiApi.login(LoginRequest(email, password))

    suspend fun getProjects() = quwiApi.getAllProjects()

    suspend fun update(id: Long, name: String): Project{
        return quwiApi.update(id, mapOf<String, String>(Pair("name", name)))
    }
}