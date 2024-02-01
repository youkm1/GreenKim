package com.example.greenkim.api.auth.DTO.Signup

import com.google.gson.annotations.SerializedName

data class SignUpResponseDto(
    @SerializedName("status")val status:Int,
    @SerializedName("success")val success:Boolean,
    @SerializedName("message")val message:String
)
