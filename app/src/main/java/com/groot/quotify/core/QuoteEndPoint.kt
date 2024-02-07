package com.groot.quotify.core

object QuoteEndPoint {

    val BASE_QUOTE = "https://favqs.com/"

    val API = "api/"

    // Quotes
    val QUOTE_OF_DAY = "qotd"
    val QUOTES = "quotes/"


    val QUOTE_OF_THE_DAY = BASE_QUOTE + API + QUOTE_OF_DAY
    val QUOTE_LIST = BASE_QUOTE + API + QUOTES
}