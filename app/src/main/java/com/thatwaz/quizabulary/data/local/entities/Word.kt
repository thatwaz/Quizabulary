package com.thatwaz.quizabulary.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val note: String?,
    val isComplete: Boolean = false // Flag for incomplete entries
)

