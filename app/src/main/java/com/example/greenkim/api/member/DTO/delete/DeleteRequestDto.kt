package com.example.greenkim.api.member.DTO.delete

import com.google.gson.annotations.SerializedName

data class DeleteRequestDto (
    @SerializedName("password") val password:String
)