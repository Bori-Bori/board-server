package com.boribori.boardserver.common

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ResponseOfGetBookContent (
        // mutalble list -> 아래 필드 가지고있는 dto로 변환해주어야함
        @SerializedName("isbn"        ) var isbn        : String?  = null,
        @SerializedName("title"       ) var title       : String?  = null,
        @SerializedName("author"      ) var author      : String?  = null,
        @SerializedName("description" ) var description : String?  = null,
        @SerializedName("imagePath"   ) var imagePath   : String?  = null,
        @SerializedName("price"       ) var price       : Int?     = null,
        @SerializedName("publisher"   ) var publisher   : String?  = null,
        @SerializedName("page"        ) var page        : Int?     = null,
        @SerializedName("pubDate"     ) var pubDate     : LocalDate?  = null,
        @SerializedName("adult"       ) var adult       : Boolean? = null,
        @SerializedName("category1"       ) var category1       : String?  = null,
        @SerializedName("category2"       ) var category2       : String?  = null,
        @SerializedName("category3"       ) var category3       : String?  = null,

)