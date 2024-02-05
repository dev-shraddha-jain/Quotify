package com.groot.quotify.dto

import kotlinx.serialization.Serializable


@Serializable
data class QotdResponse(
    val qotd_date: String,
    val quote: Quote
)