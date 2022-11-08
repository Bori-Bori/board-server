package com.boribori.boardserver.reply.dto

data class ResponseOfGetReplyList(
        var items: MutableList<ResponseOfGetReply> = mutableListOf(),
        var currentPage: Int,
        var size: Int
)
