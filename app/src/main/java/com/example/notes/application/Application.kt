package com.example.notes.application

import android.app.Application
import com.example.notes.ApplicationDatabase

class Application: Application() {
    val database: ApplicationDatabase by lazy { ApplicationDatabase.getDatabase(this) }
}