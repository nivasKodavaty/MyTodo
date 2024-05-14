package com.example.mytodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

@Composable
fun UpdateScreen(
    navController: NavController,
    viewModel: AppViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
//            viewModel.updateTodo(Todo().apply { title = updateTitle;description = textDescription })
            navController.popBackStack()
        }) {
            Text(text = "Save")
        }

    }

}