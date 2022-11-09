package com.boribori.boardserver.util

import com.boribori.boardserver.board.exception.NotFoundBookException
import com.boribori.boardserver.common.Response
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriBuilderFactory
import org.springframework.web.util.UriComponentsBuilder

@Component
class RestTemplateFactory{
    val objectMapper: ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())
    val restTemplate: RestTemplate = RestTemplate()

    fun getHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return headers
    }

    fun getHttpEntity(): HttpEntity<Any> {
        return HttpEntity(getHeader())
    }

    fun exchage(isbn: String): ResponseOfGetBook {
        var params : HashMap<String, String> = HashMap();
        params.put("isbn", isbn)
        val response : String? = restTemplate.getForObject<String>("http://localhost:8081/api/search/book?isbn="+isbn,getHttpEntity(), String :: class)

        val result = objectMapper.readValue(response, ResponseOfGetBook::class.java);
        result.content?: throw NotFoundBookException("해당하는 책을 찾을 수 없습니다.");
        return result;
    }

}