package com.example.greenkim.api.auth.DTO

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)
