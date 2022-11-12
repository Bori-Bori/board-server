package com.boribori.boardserver.board.dto.request

import com.google.gson.annotations.SerializedName

data class RequestOfSearchBooks(

        @SerializedName("category1" )var category1: String? = "",
        @SerializedName("category2" )var category2: String? = "",
        @SerializedName("category3" )var category3: String? = "",
        @SerializedName("keyword" )var keyword: String? = "",
        @SerializedName("queryType" )var queryType: String? = "keyword" //keyword, title, author
)