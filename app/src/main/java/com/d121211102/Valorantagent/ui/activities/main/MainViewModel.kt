package com.d121211102.Valorantagent.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211102.Valorantagent.Valorantagent
import com.d121211102.Valorantagent.data.models.Agent
import com.d121211102.Valorantagent.data.repository.AgentRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class  Success(val agents: List<Agent>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val agentRepository: AgentRepository): ViewModel() {

    //    initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getAgents() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = agentRepository.getAgents()
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getAgents()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Valorantagent)
                val booksRepository = application.container.agentRepository
                MainViewModel(booksRepository)
            }
        }
    }
}