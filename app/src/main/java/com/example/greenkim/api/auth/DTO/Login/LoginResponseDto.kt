package com.example.greenkim.api.auth.DTO.Login

import com.example.greenkim.api.auth.DTO.Data
import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("code")val status: Int,
    @SerializedName("success")val success: Boolean,
    @SerializedName("msg")val message:String,
    @SerializedName("data")val data: Data

)
