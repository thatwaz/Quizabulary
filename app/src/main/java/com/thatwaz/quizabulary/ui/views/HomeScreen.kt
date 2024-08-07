package com.thatwaz.quizabulary.ui.views



import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    // Set up the speech recognizer
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
        spokenText?.let { searchQuery = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quizabulary") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Quizabulary!",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Search for a word") },
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak the word to look up")
                            }
                            speechRecognizerLauncher.launch(intent)
                        }
                    ) {
                        Icon(Icons.Default.Mic, contentDescription = "Speak")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (searchQuery.isNotBlank()) {
                            navController.navigate("word_definition_screen/$searchQuery")
                        }
                    },
                    enabled = searchQuery.isNotBlank()
                ) {
                    Text("Look Up Word")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { navController.navigate("vocabulary_list_screen") }) {
                    Text("View Vocabulary List")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.navigate("start_quiz_screen") }) {
                    Text("Start Quiz")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.navigate("quick_add_screen") }) {
                    Text("Quick Add Word")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.navigate("draft_words_screen") }) {
                    Text("Draft Words")
                }
            }
        }
    )
}


//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//    var searchQuery = remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Quizabulary") }
//            )
//        },
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome to Quizabulary!",
//                    style = MaterialTheme.typography.headlineMedium,
//                    textAlign = TextAlign.Center
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//
//                OutlinedTextField(
//                    value = searchQuery.value,
//                    onValueChange = { searchQuery.value = it },
//                    label = { Text("Search for a word") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(
//                    onClick = {
//                        if (searchQuery.value.isNotBlank()) {
//                            navController.navigate("word_definition_screen/${searchQuery.value}")
//                        }
//                    },
//                    enabled = searchQuery.value.isNotBlank()
//                ) {
//                    Text("Look Up Word")
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
////                Button(onClick = { navController.navigate("add_new_word_screen") }) {
////                    Text("Add New Word")
////                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("vocabulary_list_screen") }) {
//                    Text("View Vocabulary List")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("start_quiz_screen") }) {
//                    Text("Start Quiz")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("quick_add_screen") }) {
//                    Text("Quick Add Word")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("draft_words_screen") }) {
//                    Text("Draft Words")
//                }
//            }
//        }
//    )
//}





//package com.thatwaz.quizabulary.ui.views
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Quizabulary") }
//            )
//        },
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome to Quizabulary!",
//                    style = MaterialTheme.typography.headlineMedium,
//                    textAlign = TextAlign.Center
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Button(onClick = { navController.navigate("add_new_word_screen") }) {
//                    Text("Add New Word")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("vocabulary_list_screen") }) {
//                    Text("View Vocabulary List")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("start_quiz_screen") }) {
//                    Text("Start Quiz")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("quick_add_screen") }) {
//                    Text("Quick Add Word")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { navController.navigate("draft_words_screen") }) {
//                    Text("Draft Words")
//                }
//            }
//        }
//    )
//}






//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Quizabulary") }
//            )
//        },
//        content = { paddingValues ->  // contentPadding passed here
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)  // Apply content padding here
//                    .padding(16.dp),  // Additional padding
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome to Quizabulary!",
//                    style = MaterialTheme.typography.headlineMedium,
//                    textAlign = TextAlign.Center
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Button(onClick = { /* Navigate to Add New Word screen */ }) {
//                    Text("Add New Word")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { /* Navigate to Vocabulary List screen */ }) {
//                    Text("View Vocabulary List")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(onClick = { /* Navigate to Start Quiz screen */ }) {
//                    Text("Start Quiz")
//                }
//
//                Button(onClick = { navController.navigate("quick_add_screen") }) {
//                    Text("Quick Add Word")
//                }
//
//            }
//        }
//    )
//}

