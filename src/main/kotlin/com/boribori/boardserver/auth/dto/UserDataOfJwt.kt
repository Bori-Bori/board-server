package com.boribori.boardserver.auth.dto

data class UserDataOfJwt (
        /**
         * jwt subject
         */
        val id: String,

        /**
         * jwt payload 내에 존재하는 요청자 nickname
         */
        val nickname: String

){

}