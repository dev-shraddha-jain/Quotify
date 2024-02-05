package com.groot.quotify.dto

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val favorites_count: Int?,
    val dialogue: Boolean? = false,
    val favorite: Boolean? = false,
    val tags: List<String>,
    val url: String? = null,
    val upvotes_count: Int,
    val downvotes_count: Int,
    val author: String? = null,
    val author_permalink: String? = null,
    val body: String? = null
)
