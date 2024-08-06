package com.thatwaz.quizabulary.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class WordWithDefinitions(
    @Embedded val word: Word,
    @Relation(
        parentColumn = "id",
        entityColumn = "wordId"
    )
    val definitions: List<Definition>
)
