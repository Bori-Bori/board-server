package com.boribori.boardserver.board.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseOfGetBooksContent (

        @SerializedName("item"         ) var item         : ArrayList<ResponseOfSearchItem> = arrayListOf(),
        @SerializedName("startIndex"   ) var startIndex   : Int?            = 0,
        @SerializedName("itemsPerPage" ) var itemPerPage : Int?            = 5,
        @SerializedName("totalResults")  var totalResults: Int? = 0,
){
}