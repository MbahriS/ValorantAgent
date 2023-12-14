package com.d121211102.Valorantagent.data.repository

import com.d121211102.Valorantagent.data.response.GetAgentResponse
import com.d121211102.Valorantagent.data.source.remote.ApiService

class AgentRepository (private val apiService: ApiService) {

    suspend fun getAgents(): GetAgentResponse {
        return apiService.getAgents()
    }
}