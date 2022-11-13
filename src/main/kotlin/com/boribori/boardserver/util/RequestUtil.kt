package com.boribori.boardserver.util

import com.boribori.boardserver.board.dto.request.RequestOfSearchBooks
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RequestUtil (
        private val restTemplateFactory: RestTemplateFactory
        ){

    fun getIsbn(isbn : String) : ResponseOfGetBook {
        return restTemplateFactory.exchage(isbn)
    }

    fun searchBookList(requestOfSearchBooks: RequestOfSearchBooks, pageable: Pageable){
        
    }

}