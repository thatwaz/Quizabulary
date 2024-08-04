package com.thatwaz.quizabulary.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuizabularyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize anything that needs to be set up when the app starts
    }
}
