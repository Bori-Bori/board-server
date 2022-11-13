package com.boribori.boardserver.util

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.request.RequestOfSearchBooks
import com.boribori.boardserver.board.dto.response.ResponseOfGetBooks
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import org.springframework.stereotype.Component

@Component
class RequestUtil (
        private val restTemplateFactory: RestTemplateFactory
        ){

    fun getIsbn(isbn : String) : ResponseOfGetBook {
        return restTemplateFactory.exchage(isbn)
    }

    fun searchBookList(requestOfGetBooks: RequestOfGetBooks) : ResponseOfGetBooks{
        return restTemplateFactory.getBooks(requestOfGetBooks)
    }

}