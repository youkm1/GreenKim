package com.example.greenkim.api.auth.DTO.Refresh

import com.example.greenkim.api.auth.DTO.Data
import com.google.gson.annotations.SerializedName

data class RefreshResponseDto(
    @SerializedName("status")val status: Int,
    @SerializedName("success")val success: Boolean,
    @SerializedName("message")val message:String,
    @SerializedName("data")val data:List<Data>

)
