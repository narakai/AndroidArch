package com.clem.arch_core.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.io.Reader

inline fun <reified T> gsonFromJson(string: String): T = Gson().fromJson(string, T::class.java)

inline fun <reified T> gsonFromJsonByType(string: String): T = Gson().fromJson(string, object : TypeToken<T>() {}.type)

inline fun <reified T> gsonFromJson(reader: Reader): T = Gson().fromJson(reader, T::class.java)

inline fun <reified T> gsonFromJson(jsonElement: JsonElement): T = Gson().fromJson(jsonElement, T::class.java)