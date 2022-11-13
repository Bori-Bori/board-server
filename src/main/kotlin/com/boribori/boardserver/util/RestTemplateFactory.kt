package com.boribori.boardserver.util

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.response.ResponseOfGetBooks
import com.boribori.boardserver.board.exception.NotFoundBookException
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.yaml.snakeyaml.util.UriEncoder
import java.net.URLEncoder

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
        val response : String? = restTemplate.getForObject<String>("http://localhost:8081/api/search/book?isbn="+isbn,getHttpEntity(), String :: class)

        val result = objectMapper.readValue(response, ResponseOfGetBook::class.java)
        result.content?: throw NotFoundBookException("해당하는 책을 찾을 수 없습니다.");
        return result;
    }
    fun getBooks(requestOfSearchBooks: RequestOfGetBooks): ResponseOfGetBooks{
        var url = "http://localhost:8081/api/search/books?query=" +
                requestOfSearchBooks.keyword + "&start=" +
                (requestOfSearchBooks.size * requestOfSearchBooks.page + 1).toString()+
                "&maxResults="+ requestOfSearchBooks.size.toString() + "&queryType=" +
                requestOfSearchBooks.queryType +
                "&output=js"

        val response : String? = restTemplate.getForObject<String>(url, getHttpEntity(), String :: class)

        return objectMapper.readValue(response, ResponseOfGetBooks::class.java)

    }

}