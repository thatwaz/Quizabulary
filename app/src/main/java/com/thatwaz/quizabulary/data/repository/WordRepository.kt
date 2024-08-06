package com.thatwaz.quizabulary.data.repository

import com.thatwaz.quizabulary.data.local.dao.WordDao
import com.thatwaz.quizabulary.data.local.entities.Word
import com.thatwaz.quizabulary.network.RetrofitInstance
import com.thatwaz.quizabulary.network.WordDefinitionResponse
import com.thatwaz.quizabulary.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordRepository @Inject constructor(private val wordDao: WordDao) {


    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    fun getIncompleteWords(): Flow<List<Word>> {
        return wordDao.getIncompleteWords()
    }

    fun getWordDefinition(word: String): Flow<Resource<List<WordDefinitionResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = RetrofitInstance.api.getWordDefinition(word)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("No definitions found"))
            } else {
                emit(Resource.Error("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Network error: ${e.message()}"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        }
    }
}


//package com.thatwaz.quizabulary.data.repository
//
//import com.thatwaz.quizabulary.data.local.dao.WordDao
//import com.thatwaz.quizabulary.data.local.entities.Word
//import com.thatwaz.quizabulary.network.RetrofitInstance
//import com.thatwaz.quizabulary.network.WordDefinitionResponse
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class WordRepository @Inject constructor(private val wordDao: WordDao) {
//
//    suspend fun insertWord(word: Word) {
//        wordDao.insertWord(word)
//    }
//
//    fun getIncompleteWords(): Flow<List<Word>> {
//        return wordDao.getIncompleteWords()
//    }
//
//    fun getWordById(wordId: Int): Flow<Word?> {
//        return wordDao.getWordById(wordId)
//    }
//
//    fun getWordDefinition(word: String): Flow<Result<List<WordDefinitionResponse>>> = flow {
//        try {
//            val response = RetrofitInstance.api.getWordDefinition(word)
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    emit(Result.success(it))
//                } ?: emit(Result.failure(Exception("No definitions found")))
//            } else {
//                emit(Result.failure(Exception("Error: ${response.code()} ${response.message()}")))
//            }
//        } catch (e: HttpException) {
//            emit(Result.failure(e))
//        } catch (e: Exception) {
//            emit(Result.failure(e))
//        }
//    }
//}

