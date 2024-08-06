package com.thatwaz.quizabulary.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thatwaz.quizabulary.data.local.entities.Word
import com.thatwaz.quizabulary.viewmodel.WordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraftWordsScreen(
    navController: NavController,
    viewModel: WordViewModel = hiltViewModel()
) {
    val incompleteWords = viewModel.getWordsInDraftState().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Incomplete Words") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                if (incompleteWords.value.isEmpty()) {
                    Text(text = "No incomplete words found.")
                } else {
                    LazyColumn {
                        items(incompleteWords.value) { word ->
                            WordItem(word = word, onClick = {
                                // Navigate to WordDetail screen or any other action
                                navController.navigate("wordDetail/${word.id}")
                            })
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun WordItem(word: Word, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = word.word, style = MaterialTheme.typography.bodyMedium)
            if (!word.note.isNullOrEmpty()) {
                Text(text = word.note, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

