package com.groot.quotify


import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

class Platform constructor() {
    val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}

fun initLogger() {
    Napier.base(DebugAntilog())
}