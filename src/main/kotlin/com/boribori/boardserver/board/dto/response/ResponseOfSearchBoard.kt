package com.boribori.boardserver.board.dto.response

import com.boribori.boardserver.common.ResponseOfGetBookContent

data class ResponseOfSearchBoard(
        var isbn: String?,
        var title : String?,
        var imagePath : String?,
        var author : String?,
        var commentCount : Int?
){

}