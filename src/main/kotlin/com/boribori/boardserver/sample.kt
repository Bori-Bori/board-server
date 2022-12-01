package com.boribori.boardserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.runApplication
import java.time.LocalDateTime

class sample
    fun main(args: Array<String>) {
        var temp : LocalDateTime = LocalDateTime.now();

        var objectMapper = ObjectMapper()

        objectMapper.registerModule(JavaTimeModule())
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)


        print(objectMapper.writeValueAsString(temp))

    }

