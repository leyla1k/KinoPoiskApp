package com.example.android_task.utils

import com.example.android_task.model.simple.Film
import com.example.android_task.model.dto.FilmResponseDto
import com.example.android_task.model.simple.Poster
import com.example.android_task.model.dto.PostersResponseDto


fun FilmResponseDto.toFilm(): Film {

    return Film(this.id, this.name, this.description, this.posters, this.rating)
}

fun PostersResponseDto.toPosters(): List<Poster> {
    return if (this.urls!=null) {
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

fun parseCountries(input: String?): List<String> {
    if (input == null) {
        return listOf()
    }
    return input.split(", ").map { it.trim() }
}


/*fun mapToEndPoint(//ловить 400 ошбику!!!
    filter: Filter
): String {
    val endPoint: StringBuilder = StringBuilder("")
    // ?year=2023&genres.name=криминал&genres.name=драма

    var i = 0
    while (i < filter.listYears.size) {
        endPoint.append("year=${filter.listYears[i]}&")
        i += i
    }
    i = 0
    while (i < filter.listAgeRating.size) {
        endPoint.append("ageRating=${filter.listAgeRating[i]}&")
        i += i
    }
    i = 0
    while (i < filter.listCountries.size) {
        endPoint.append("countries.name=${filter.listCountries[i]}&")
        i += i
    }
    endPoint.deleteCharAt(endPoint.lastIndex)

    return endPoint.toString()
}*/


