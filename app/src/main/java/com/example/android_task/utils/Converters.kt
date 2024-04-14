package com.example.android_task.utils

import com.example.android_task.model.FilmEntity
import com.example.android_task.model.dto.FilmResponseDto
import com.example.android_task.model.dto.PostersResponseDto
import com.example.android_task.model.simple.Film
import com.example.android_task.model.simple.Poster

fun FilmResponseDto.toFilm(): Film {

    return Film(this.id, this.name, this.description, this.posters, this.rating)
}

fun Film.toFilmEntity(): FilmEntity {
    return FilmEntity(this.id, this.name, this.description, this.poster, this.rating)
}

fun PostersResponseDto.toPosters(): List<Poster> {
    return if (this.urls != null) {
        this.urls.map {
            Poster(it.url, null)
        }
    } else {
        listOf()
    }
}

fun parseYears(input: String): List<String>? {
    val regex =
        Regex("(?:\\b\\d{4}\\b|\\b\\d{4}-\\d{4}\\b)(?:, (?:\\b\\d{4}\\b|\\b\\d{4}-\\d{4}\\b))*")
    if (input == "") {
        return listOf()
    }
    if (!regex.matches(input)) {
        // Строка не соответствует формату, возвращаем null
        return null
    }
    return input.split(", ").map { it.trim() }
}

fun parseAgeRating(input: String): List<String>? {
    val regex =
        Regex("(?:\\b\\d{1,3}\\b|\\b\\d{1,3}-\\d{1,3}\\b)(?:, (?:\\b\\d{1,3}\\b|\\b\\d{1,3}-\\d{1,3}\\b))*")
    if (input == "") {
        return listOf()
    }
    if (!regex.matches(input)) {
        // Строка не соответствует формату, возвращаем null
        return null
    }
    return input.split(", ").map { it.trim() }
}






