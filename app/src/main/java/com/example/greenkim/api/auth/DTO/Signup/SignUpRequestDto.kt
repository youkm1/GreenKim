package com.example.greenkim.api.auth.DTO.Signup

import com.google.gson.annotations.SerializedName

data class SignUpRequestDto(
    @SerializedName("email")val email:String,
    @SerializedName("password")val password:String,
    @SerializedName("name")val name:String,
)
