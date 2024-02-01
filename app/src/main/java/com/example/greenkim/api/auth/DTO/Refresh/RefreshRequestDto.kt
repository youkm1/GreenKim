package com.example.greenkim.api.auth.DTO.Refresh

import com.google.gson.annotations.SerializedName

data class RefreshRequestDto(
    @SerializedName("accessToken") val accessToken:String,
    @SerializedName("refreshToken") val refreshToken:String,
)
