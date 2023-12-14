package com.d121211102.Valorantagent.data

import com.d121211102.Valorantagent.data.repository.AgentRepository
import com.d121211102.Valorantagent.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val agentRepository: AgentRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL = "https://valorant-api.com/"

    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val agentRepository: AgentRepository
        get() = AgentRepository(retrofitService)
}