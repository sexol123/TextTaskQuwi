package com.example.testtask.data.response

data class Project(
    val id: Long,
    val id_channel: Int,
    val is_active: Int,
    val logo_url: String,
    val name: String,
    val position: Int,
    val uid: String
)