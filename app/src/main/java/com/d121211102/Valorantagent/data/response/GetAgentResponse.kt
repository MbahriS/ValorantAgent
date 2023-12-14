package com.d121211102.Valorantagent.data.response

import com.d121211102.Valorantagent.data.models.Agent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgentResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("data")
    val data: List<Agent>,
)