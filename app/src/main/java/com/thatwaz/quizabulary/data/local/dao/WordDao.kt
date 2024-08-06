package com.thatwaz.quizabulary.data.local.dao



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.thatwaz.quizabulary.data.local.entities.Word
import com.thatwaz.quizabulary.data.local.entities.WordWithDefinitions
import kotlinx.coroutines.flow.Flow



@Dao
interface WordDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM words WHERE isComplete = 0")
    fun getIncompleteWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE id = :wordId")
    fun getWordById(wordId: Int): Flow<Word?>
}



