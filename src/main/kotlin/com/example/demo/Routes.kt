package com.example.demo

import com.samskivert.mustache.Mustache
import org.springframework.context.MessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.RenderingResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.toMono
import java.util.*

class Routes(//private val userHandler: UserHandler,
             /*private val messageSource: MessageSource*/) {

    fun router() = router {
        accept(TEXT_HTML).nest {
            GET("/api").nest {
                accept(APPLICATION_JSON).nest {
                    GET("/ok", {ok().body(Flux.just(OK("OK")))})
                }
            }
            //GET("/sse") { ok().render("sse") }
            //GET("/users", userHandler::findAllView)
        }
        //"/api".nest {
            /*accept(APPLICATION_JSON).nest {
            GET("/users", userHandler::findAll)
        }
        accept(TEXT_EVENT_STREAM).nest {
            GET("/users", userHandler::stream)
        }*/
        //}
        resources("/**", ClassPathResource("static/"))
    }/*.filter { request, next ->
        next.handle(request).flatMap {
            if (it is RenderingResponse) RenderingResponse.from(it).modelAttributes(attributes(request.locale(), messageSource)).build() else it.toMono()
        }
    }*/

    private fun attributes(locale: Locale, messageSource: MessageSource) = mutableMapOf<String, Any>(
            "i18n" to Mustache.Lambda { frag, out ->
                val tokens = frag.execute().split("|")
                out.write(messageSource.getMessage(tokens[0], tokens.slice(IntRange(1, tokens.size - 1)).toTypedArray(), locale)) })

}