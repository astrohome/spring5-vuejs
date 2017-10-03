package com.example.demo

import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.server.WebHandler

fun beans() = beans {
    //bean<UserHandler>()
    bean<Routes>()
    bean("webHandler") {
        RouterFunctions.toWebHandler(
                ref<Routes>().router(),
                HandlerStrategies.builder().build()
                //HandlerStrategies.builder().viewResolver(ref()).build()
        )
    }
    /*bean("messageSource") {
        ReloadableResourceBundleMessageSource().apply {
            setBasename("messages")
            setDefaultEncoding("UTF-8")
        }
    }*/
}