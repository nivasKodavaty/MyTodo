package com.example.mytodo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mytodo.ViewModel.AppViewModel
import com.example.mytodo.data.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    navController: NavHostController,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
) {

    val todoList = viewModel.homeState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("ToDo List App")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("Insert")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            val list = todoList.value.showList
            if (list.isNotEmpty()) {
                items(list) {
                    Listcard(navController, todo = it)
                    Log.d("Display", "Greeting:${it.title} ")
                }
            } else {
                item {
                    Text(text = "No Data")
                }
            }
        }
    }


}

@Composable
fun Listcard(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    todo: Todo,
    viewModel: AppViewModel = viewModel()
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text(text = todo.title, fontSize = 30.sp, modifier = Modifier.weight(5f))

            IconButton(
                onClick = {
                    navController.navigate("Update/${todo.id}")
                }, modifier = Modifier
                    .weight(1f)
                    .padding()
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Add")
            }
            IconButton(onClick = { viewModel.deleteTodo(todo) }, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Delete, contentDescription = "Add")
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Card {
                Text(text = todo.description, fontSize = 20.sp)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingP() {
    Listcard(navController = NavHostController(LocalContext.current), todo = Todo().apply {
        title = "Title"
        description = "Description"
    })
}
