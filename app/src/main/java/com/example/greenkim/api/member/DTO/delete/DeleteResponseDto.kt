package com.example.greenkim.api.member.DTO.delete

import com.google.gson.annotations.SerializedName

data class DeleteResponseDto (
    @SerializedName("status")val status:Int,
    @SerializedName("success")val success:Boolean,
    @SerializedName("message") val message:String
)