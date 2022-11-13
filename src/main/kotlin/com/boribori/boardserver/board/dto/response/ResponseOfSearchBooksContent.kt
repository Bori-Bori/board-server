package com.boribori.boardserver.board.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseOfSearchBooksContent (

        @SerializedName("item"         ) var responseOfSearchItem         : ArrayList<ResponseOfSearchItem> = arrayListOf(),
        @SerializedName("startIndex"   ) var startIndex   : Int?            = null,
        @SerializedName("itemsPerPage" ) var itemsPerPage : Int?            = null,
)