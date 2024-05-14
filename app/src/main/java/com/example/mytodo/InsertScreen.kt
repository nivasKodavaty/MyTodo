package com.example.mytodo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mytodo.ViewModel.AppViewModel
import com.example.mytodo.data.Todo

@Composable
fun InsertScreen(navController: NavController,viewModel: AppViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var textTitle by remember {
            mutableStateOf("")
        }
        var textDescription by remember {
            mutableStateOf("")
        }

        TextField(value = textTitle, onValueChange = {
                newTitle ->
            textTitle = newTitle
        },label = {
            Text(text = "Title")
        }, modifier=modifier.padding(50.dp))
        TextField(value = textDescription, onValueChange = {
                newTitle ->
            textDescription = newTitle
        },label = {
            Text(text ="Description")
        }, )
        Button(onClick = {
            viewModel.addTodo(Todo().apply { title=textTitle;description=textDescription })
            Log.d("TAG", "InsertScreen:$textTitle$textDescription")
            navController.popBackStack()
        }) {
            Text(text = "Save")
        }

    }

}