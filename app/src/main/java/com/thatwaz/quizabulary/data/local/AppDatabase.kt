package com.thatwaz.quizabulary.data.local



import androidx.room.Database
import androidx.room.RoomDatabase
import com.thatwaz.quizabulary.data.local.dao.WordDao
import com.thatwaz.quizabulary.data.local.entities.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
