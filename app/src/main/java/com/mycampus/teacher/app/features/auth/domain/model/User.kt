package com.mycampus.teacher.app.features.auth.domain.model


data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String? = null
)