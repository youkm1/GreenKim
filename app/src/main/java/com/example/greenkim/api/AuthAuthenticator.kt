package com.example.greenkim.api

import com.example.greenkim.api.auth.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
):Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenManager.getAccessToken().first()
        }

        if (refreshToken == null || refreshToken =="LOGIN") {
            response.close()
            return null
        }

        return newRequestWithToken(refreshToken,response.request)
    }

    private fun newRequestWithToken(token:String, request: Request):Request =
        request.newBuilder()
            .header("AUTHORIZATION",token)
            .build()

}