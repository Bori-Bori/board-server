package com.boribori.boardserver.comment.dto

import org.springframework.web.bind.annotation.RequestParam

data class RequestOfGetComment(
        var order : String = "recent",
        @RequestParam()var bookPage : String = "1"
) {
}