package com.thatwaz.quizabulary.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.thatwaz.quizabulary.data.local.Word
import com.thatwaz.quizabulary.data.local.WordWithDefinitions

@Dao
interface WordDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM words WHERE isComplete = 0")
    fun getIncompleteWords(): LiveData<List<Word>>

    @Transaction
    @Query("SELECT * FROM words WHERE id = :wordId")
    fun getWordWithDefinitions(wordId: Int): LiveData<WordWithDefinitions>

    // Other CRUD operations...
}

