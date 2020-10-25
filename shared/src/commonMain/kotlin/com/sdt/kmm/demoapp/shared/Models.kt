package com.sdt.kmm.demoapp.shared

import kotlinx.serialization.*


@Serializable
data class Response<T>(
    @SerialName("status") val status: Status,
    @SerialName("data") val data: T
)

@Serializable
data class Status(
    @SerialName("value") val value: Int,
    @SerialName("description") val description: String
)

@Serializable
data class Error(
    @SerialName("code") val code: Int,
    @SerialName("cause") val cause: String
)

@Serializable
data class LoginData(
    @SerialName("token") val token: String,
    @SerialName("user") val user: User
)

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("userName") val userName: String,
    @SerialName("displayName") val displayName: String
)
