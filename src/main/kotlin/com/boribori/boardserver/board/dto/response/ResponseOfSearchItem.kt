package com.boribori.boardserver.board.dto.response

import com.google.gson.annotations.SerializedName


data class ResponseOfSearchItem (
        @SerializedName("isbn13"        ) var isbn        : String?  = null,
        @SerializedName("title"       ) var title       : String?  = null,
        @SerializedName("author"      ) var author      : String?  = null,
        @SerializedName("imagePath"   ) var imagePath   : String?  = null,
        @SerializedName("publisher"   ) var publisher   : String?  = null
)