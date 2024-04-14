package com.example.android_task.utils

object Global {

    const val BASE_URL = "https://api.kinopoisk.dev/"
    const val TABLE_NAME = "films"
    const val END_POINT: String = "v1.4/movie"
    const val END_POINT_SEARCH: String = "v1.4/movie/search"
    const val END_POINT_IMAGE: String = "v1.4/image"
    const val END_POINT_REVIEW: String = "v1.4/review"

    val COUNTRIES: List<String> = listOf("Россия", "США", "Франция","Венгрия","Польша","Сербия")//не успела реализовать метод и пагинацию чтоб все страны были
    val GENRES: List<String> = listOf("драма", "криминал", "комедия","ужасы","мелодрама")//не успела реализовать метод и пагинацию чтоб все страны были
}