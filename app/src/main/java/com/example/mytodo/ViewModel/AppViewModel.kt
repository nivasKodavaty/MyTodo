package com.example.mytodo.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.data.RealmClient
import com.example.mytodo.data.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private var realmClient: RealmClient = RealmClient

    init {
        observeTodos()
    }

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            realmClient.addTodo(todo)
        }
    }

    private fun observeTodos() {
        viewModelScope.launch {
            realmClient.getAllTodoAsFlow().collect {
                _homeState.value = HomeState(showList = it)
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        realmClient.deleteTodo(todo)
    }

//    fun updateTodo(todo: Todo) {
//        repository.update(todo)
//    }

}

data class HomeState(
    val loading: Boolean = false,
    val showList: List<Todo> = listOf()
)