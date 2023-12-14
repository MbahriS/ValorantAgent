package com.d121211102.Valorantagent.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.d121211102.Valorantagent.data.response.GetAgentResponse

interface ApiService {
    @GET("v1/agents")
    suspend fun getAgents(
        @Query("status") status: Int = 200,
    ): GetAgentResponse
}