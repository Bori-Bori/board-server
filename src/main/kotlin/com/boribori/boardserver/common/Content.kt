package com.boribori.boardserver.common

import com.google.gson.annotations.SerializedName

data class Content (

        @SerializedName("isbn"        ) var isbn        : String?  = null,
        @SerializedName("title"       ) var title       : String?  = null,
        @SerializedName("author"      ) var author      : String?  = null,
        @SerializedName("description" ) var description : String?  = null,
        @SerializedName("imagePath"   ) var imagePath   : String?  = null,
        @SerializedName("price"       ) var price       : Int?     = null,
        @SerializedName("publisher"   ) var publisher   : String?  = null,
        @SerializedName("page"        ) var page        : Int?     = null,
        @SerializedName("pubDate"     ) var pubDate     : String?  = null,
        @SerializedName("adult"       ) var adult       : Boolean? = null

)