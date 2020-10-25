package com.sdt.kmm.demoapp.shared

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

//@InternalSerializationApi
//class ResponseSerializer<T>(private val dataSerializer: KSerializer<T>) : KSerializer<Response<T>> {
//
//    @ExperimentalSerializationApi
//    @InternalSerializationApi
//    override val descriptor: SerialDescriptor =
//        buildSerialDescriptor("", PolymorphicKind.SEALED) {
//            element("", dataSerializer.descriptor)
//        }
//
//    @InternalSerializationApi
//    override fun deserialize(decoder: Decoder): Response<T> {
//        // Decoder -> JsonDecoder
//        require(decoder is JsonDecoder) // this class can be decoded only by Json
//        // JsonDecoder -> JsonElement
//        val element = decoder.decodeJsonElement()
//        // JsonElement -> value
//        if (element is JsonObject && "errors" in element){
//            return Json.decodeFromJsonElement<Response.Failure>(element)
//        }
//        return Json.decodeFromJsonElement<Response.Success<T>>(element)
//    }
//
//    override fun serialize(encoder: Encoder, value: Response<T>) {
//        // Encoder -> JsonEncoder
//        require(encoder is JsonEncoder) // This class can be encoded only by Json
//        // value -> JsonElement
//        val element = when (value) {
//            is Response.Success -> encoder.json.encodeToJsonElement(dataSerializer, value.data)
//            is Response.Failure -> buildJsonObject { put("error", value.message) }
//        }
//        // JsonElement -> JsonEncoder
//        encoder.encodeJsonElement(element)
//    }
//}
