package com.groot.quotify.base

import com.groot.quotify.core.QuoteEndPoint.QUOTE_OF_THE_DAY
import com.groot.quotify.dto.QotdResponse
import com.groot.quotify.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class QuoteApiModel {

    private fun httpClient(): HttpClient {
        return HttpClient() {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "HTTP Client", message = message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    })
            }
        }.also { initLogger() }
    }


    //https://favqs.com/api/qotd
    suspend fun getQuoteOfTheDay(): QotdResponse {
        val httpClient = httpClient()
        val apiResponse = httpClient.get(QUOTE_OF_THE_DAY) {
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
        }
        val quoteDto: QotdResponse = apiResponse.body()
        return quoteDto
    }


}