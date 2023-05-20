package com.example.sampleapp.util

import android.content.res.AssetManager
import com.example.sampleapp.data.database.Employee

fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }

fun List<Employee>.formatToString(): String {
    return joinToString("\n\n") { employee ->
        employee.toString()
    }
}

