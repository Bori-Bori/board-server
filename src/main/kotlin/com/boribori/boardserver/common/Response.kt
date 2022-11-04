package com.boribori.boardserver.common

data class Response<T: Any>(
        val status : Status,
        val content : T
    ) {


    data class Status(
            var message : String? = null
    ){
    }

}