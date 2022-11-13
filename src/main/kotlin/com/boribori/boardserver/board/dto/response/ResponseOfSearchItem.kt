package com.boribori.boardserver.board.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseOfSearchItem (
        @SerializedName("isbn13"        ) var isbn13        : String?  = null,
        @SerializedName("title"       ) var title       : String?  = null,
        @SerializedName("author"      ) var author      : String?  = null,
        @SerializedName("cover"   ) var cover   : String?  = null,
        @SerializedName("publisher"   ) var publisher   : String?  = null
)