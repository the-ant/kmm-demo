package com.sdt.kmm.demoapp.shared

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.util.*
import kotlinx.coroutines.*


internal expect val ApplicationDispatcher: CoroutineDispatcher

class Api {

    private val client = HttpClient(OkHttp) {
        install(DefaultRequest) {
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    var address = "http://192.168.24.13:8081/v1/users/login"

    @KtorExperimentalAPI
    fun login(username: String = "test01", password: String = "1", callback: (String) -> Unit = {}) {
        android.util.Log.d("Api", "login: $address")
        CoroutineScope(ApplicationDispatcher).launch {
            try {
                client.post<ResponseData<Data>>(address) {
                    body = MultiPartFormDataContent(
                            formData {
                                append("userName", username)
                                append("password", password)
                            }
                    )
                }.also {
                    Log.d("TAG", "login: $it")
                    if (it.status.value == 200) {
//                        mToken = it.data.token
//                        mLogin.postValue(true)
                    }
                }
                client.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}

