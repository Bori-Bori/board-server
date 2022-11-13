package com.boribori.boardserver.board.dto.response

import com.boribori.boardserver.common.Status
import com.google.gson.annotations.SerializedName

data class ResponseOfGetBooks (
        @SerializedName("status"  ) var status  : Status?  = Status(),
        @SerializedName("content" ) var content : ResponseOfGetBooksContent? = ResponseOfGetBooksContent()
)