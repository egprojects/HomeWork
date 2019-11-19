package ru.egprojects.androidlab.model

data class Anime(
        val title: String,
        val posterSrc: String,
        val genre: String,
        val description: String,
        val screenshotsSrc: List<String>? = null
)