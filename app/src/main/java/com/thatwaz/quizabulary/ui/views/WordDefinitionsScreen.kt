package com.thatwaz.quizabulary.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thatwaz.quizabulary.ui.utils.Resource
import com.thatwaz.quizabulary.viewmodel.WordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDefinitionScreen(
    navController: NavController,
    word: String,
    viewModel: WordViewModel = hiltViewModel()
) {
    val wordDefinition by viewModel.wordDefinition.collectAsState()

    LaunchedEffect(key1 = word) {
        viewModel.lookUpWordDefinition(word)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Definition of $word") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                when (val result = wordDefinition) {
                    null -> Text(text = "No data", style = MaterialTheme.typography.bodyLarge)
                    is Resource.Loading -> Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
                    is Resource.Success -> {
                        result.data?.forEach { response ->
                            response.meanings.forEach { meaning ->
                                Text(
                                    text = "${meaning.partOfSpeech}:",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                meaning.definitions.forEach { definition ->
                                    Text(
                                        text = definition.definition,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                    definition.example?.let { example ->
                                        Text(
                                            text = "Example: $example",
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    is Resource.Error -> Text(text = "Error: ${result.message}", style = MaterialTheme.typography.bodyLarge)
                    else -> Text(text = "Unknown state", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    )
}


