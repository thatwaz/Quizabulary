package com.thatwaz.quizabulary.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thatwaz.quizabulary.data.local.entities.Word
import com.thatwaz.quizabulary.data.repository.WordRepository
import com.thatwaz.quizabulary.network.WordDefinitionResponse
import com.thatwaz.quizabulary.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    // StateFlow to hold the definition lookup results
    private val _wordDefinition = MutableStateFlow<Resource<List<WordDefinitionResponse>>?>(null)
    val wordDefinition: StateFlow<Resource<List<WordDefinitionResponse>>?> = _wordDefinition



    fun saveQuickAddWord(word: String, note: String?) {
        val newWord = Word(word = word, note = note, isComplete = false)
        viewModelScope.launch {
            repository.insertWord(newWord)
        }
    }

    fun getWordsInDraftState(): Flow<List<Word>> {
        return repository.getIncompleteWords()
    }

    fun lookUpWordDefinition(word: String) {
        viewModelScope.launch {
            repository.getWordDefinition(word).collect { result ->
                _wordDefinition.value = result
            }
        }
    }
}
