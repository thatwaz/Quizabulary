package com.thatwaz.quizabulary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thatwaz.quizabulary.ui.theme.QuizabularyTheme
import com.thatwaz.quizabulary.ui.views.HomeScreen
import com.thatwaz.quizabulary.ui.views.QuickAddScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Assuming this enables full-screen immersive mode
        setContent {
            QuizabularyTheme {
                val navController = rememberNavController() // Create NavController
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "home_screen", modifier = modifier) {
        composable("home_screen") { HomeScreen(navController) }
        composable("quick_add_screen") { QuickAddScreen(navController) }
        // Add other screens here
    }
}

