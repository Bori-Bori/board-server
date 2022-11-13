package com.boribori.boardserver.board.dto.response

import org.springframework.data.domain.Pageable

class ResponseOfSearchBoards(

){
    lateinit var items : MutableList<ResponseOfSearchBoard>
    var isFirst : Boolean = false
    var isLast : Boolean = true
    var page : Int = 0
    var size : Int = 5
    var query : String = ""
    fun of(boardsList: MutableList<ResponseOfSearchBoard>, pageable: Pageable, query: String): ResponseOfSearchBoards{
        var isFirst = false;
        var isLast = false;

        if(boardsList.size < pageable.pageSize){
            isLast = true;
        }

        if(pageable.pageNumber == 0){
            isFirst = true;
        }
        var result = ResponseOfSearchBoards()
        result.items = boardsList
        result.isFirst = isFirst
        result.isLast = isLast
        result.page = pageable.pageNumber
        result.size = pageable.pageSize
        result.query = query

        return result
    }
}