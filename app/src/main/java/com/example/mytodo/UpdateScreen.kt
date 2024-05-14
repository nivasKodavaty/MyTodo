package com.example.mytodo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mytodo.ViewModel.AppViewModel
import com.example.mytodo.data.Todo

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun UpdateScreen(
    navController: NavController,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val todoId = navController.currentBackStackEntry?.arguments?.getString("todoId")
        val todo = viewModel.todo.collectAsState()
        LaunchedEffect(key1 = null) {
            todoId?.let {
                viewModel.getTodoById(it)
            } ?: run {
                navController.popBackStack()
            }
        }
        var updateTitle by remember {
            mutableStateOf("")
        }
        var textDescription by remember {
            mutableStateOf("")
        }

        TextField(value = updateTitle, onValueChange = { newTitle ->
            updateTitle = newTitle
        }, label = {
            Text(text = "Title")
        }, modifier = modifier.padding(50.dp))
        TextField(
            value = textDescription,
            onValueChange = { newTitle ->
                textDescription = newTitle
            },
            label = {
                Text(text = "Description")
            },
        )
        Button(onClick = {
            viewModel.updateTodo(Todo().apply {
                id = todoId; title = updateTitle;description = textDescription
            })
            navController.popBackStack()
        }) {
            Text(text = "Save")
        }

    }

}