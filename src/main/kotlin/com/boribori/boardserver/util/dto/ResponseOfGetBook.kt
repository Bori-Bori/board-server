package com.boribori.boardserver.util.dto

import com.boribori.boardserver.common.Content
import com.boribori.boardserver.common.Status
import com.google.gson.annotations.SerializedName

data class ResponseOfGetBook (
        @SerializedName("status"  ) var status  : Status?  = Status(),
        @SerializedName("content" ) var content : Content? = Content()

)