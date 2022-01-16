package com.chndr.githubtestapp.data.model

data class ReposUserResponse(
    val id: String,
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val updated_at: String
)
