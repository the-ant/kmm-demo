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


    companion object {
        private const val TAG = "Api"

        const val BASE_URL = "http://192.168.16.32:8083/"
        const val URL_LOGIN = "${BASE_URL}v1/users/login"
        const val URL_SIGN_UP = "${BASE_URL}v1/users/register"
        const val URL_GET_PROFILE = "${BASE_URL}v1/users"
    }

    @KtorExperimentalAPI
    fun login(
        username: String,
        password: String,
        callback: (LoginData?) -> Unit = {}
    ) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                client.post<String>(URL_LOGIN) {
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
                    callback(response.data)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                callback(null)
            } finally {
                client.close()
            }
        }

    }

    @KtorExperimentalAPI
    fun register(
        username: String,
        password: String,
        displayName: String,
        callback: (Boolean) -> Unit = {}
    ) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                client.post<String>(URL_SIGN_UP) {
                    body = MultiPartFormDataContent(
                        formData {
                            append("userName", username)
                            append("password", password)
                            append("displayName", displayName)
                        }
                    )
                }.also {
                    logDebug(TAG, "register: $it")
                    val response = Json.decodeFromString<Response<String>>(it)
                    logDebug(TAG, "register: $response")
                    callback(response.status.value == 200)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                callback(false)
            } finally {
                client.close()
            }
        }

    }
}
