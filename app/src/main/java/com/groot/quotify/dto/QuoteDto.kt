package com.groot.quotify.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    val last_page: Boolean? = false,
    val page: Int,
    val quotes: List<QotdResponse>
)