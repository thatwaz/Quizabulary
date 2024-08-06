package com.thatwaz.quizabulary
//
//import com.thatwaz.quizabulary.data.local.dao.WordDao
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.thatwaz.quizabulary.data.local.AppDatabase
//import com.thatwaz.quizabulary.data.local.entities.Word
//
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.runBlocking
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import kotlin.test.assertEquals
//
//@RunWith(AndroidJUnit4::class)
//class WordDaoTest {
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var database: AppDatabase
//    private lateinit var wordDao: WordDao
//
//    @Before
//    fun setUp() {
//        database = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            AppDatabase::class.java
//        ).allowMainThreadQueries().build()
//
//        wordDao = database.wordDao()
//    }
//
//    @After
//    fun tearDown() {
//        database.close()
//    }
//
//    @Test
//    fun insertAndRetrieveWord() = runBlocking {
//        val word = Word(word = "Test", note = "This is a test")
//        wordDao.insertWord(word)
//
//        val allWords = wordDao.getIncompleteWords().first()
//        assertEquals(1, allWords.size)
//        assertEquals("Test", allWords[0].word)
//        assertEquals("This is a test", allWords[0].note)
//    }
//}
