package com.example.greenkim.api.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


//JWT 토큰을 로컬에 저장
class TokenManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }

    fun getAccessToken(): Flow<String?> {
        return dataStore.data.map { prefs->
            prefs[ACCESS_TOKEN_KEY]
        }
    }

    suspend fun saveAccessToken(token:String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = token
        }
    }
    suspend fun deleteAccessToken(){
        dataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN_KEY)
        }
    }
}