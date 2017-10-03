package com.example.demo

import org.springframework.web.reactive.function.server.ServerRequest
import java.util.*

fun ServerRequest.locale() =
        this.headers().asHttpHeaders().acceptLanguageAsLocales.firstOrNull() ?: Locale.ENGLISH!!