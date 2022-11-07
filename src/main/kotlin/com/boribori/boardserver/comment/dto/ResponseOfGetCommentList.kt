package com.boribori.boardserver.comment.dto

class ResponseOfGetCommentList(
        var items: MutableList<ResponseOfGetComment> = mutableListOf<ResponseOfGetComment>(),
        var totalPage : Int,
        var currentPage : Int,
        var size : Int
) {

}