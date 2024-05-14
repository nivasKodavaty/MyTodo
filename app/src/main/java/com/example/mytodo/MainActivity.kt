package com.example.mytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodo.ViewModel.AppViewModel
import com.example.mytodo.ui.theme.MyTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: AppViewModel = viewModel(LocalContext.current as ComponentActivity)
            NavHost(navController = navController, startDestination = "Greeting", builder = {
                composable("Greeting") {
                    Greeting(navController, viewModel)
                }
                composable("Insert") {
                    InsertScreen(navController, viewModel)
                }
                composable("Update") {
                    UpdateScreen(navController, viewModel)
                }

            })

        }
    }
}








