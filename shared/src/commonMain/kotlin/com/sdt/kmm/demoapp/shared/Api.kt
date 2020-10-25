package com.sdt.kmm.demoapp.shared

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.util.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*


internal expect val ApplicationDispatcher: CoroutineDispatcher

class Api {

    private val client = HttpClient() {
        install(DefaultRequest) {
        }
    }

    private val TAG = "Api"

    var address = "http://192.168.24.13:8081/v1/users/login"

    @KtorExperimentalAPI
    fun login(
        username: String = "test01",
        password: String = "11",
        callback: (String) -> Unit = {}
    ) {
        logDebug(TAG, "login: $address")
        CoroutineScope(ApplicationDispatcher).launch {
            try {
                client.post<String>(address) {
                    body = MultiPartFormDataContent(
                        formData {
                            append("userName", username)
                            append("password", password)
                        }
                    )
                }.also {
                    logDebug(TAG, "login: $it")
                    val response = Json.decodeFromString<Response<LoginData>>(it)
                    logDebug(TAG, "login: $response")
                }
                client.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}
