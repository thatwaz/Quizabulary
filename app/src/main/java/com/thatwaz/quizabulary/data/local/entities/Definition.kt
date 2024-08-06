package com.thatwaz.quizabulary.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definitions")
data class Definition(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wordId: Int,                       // Foreign key to associate with the Word entity
    val meaning: String,
    val exampleSentence: String? = null,
    val partOfSpeech: String? = null
)

