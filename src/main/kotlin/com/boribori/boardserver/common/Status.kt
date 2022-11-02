package com.boribori.boardserver.common

import com.google.gson.annotations.SerializedName


data class Status (

        @SerializedName("msg" ) var msg : String? = null

)