package com.example.mytodo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytodo.data.Todo
import com.example.mytodo.data.TodoDatabase
import com.example.mytodo.ui.theme.MyTodoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = TodoDatabase.getDatabase(applicationContext).getTodoDao()
        runBlocking {
            dao.insert(Todo(id = 1, title = "wtf", note = "wtf", date = "today"))
            delay(500)
            val result = dao.getAllTodos()
            withContext(Dispatchers.Main) {
                Log.d("TAG", "onCreate: $result")
            }
        }


        enableEdgeToEdge()
        setContent {
            MyTodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTodoTheme {
        Greeting("Android")
    }
}