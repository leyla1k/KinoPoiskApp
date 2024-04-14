package com.example.android_task.model.dto


import com.example.android_task.model.simple.FilmRating
import com.example.android_task.model.simple.Poster
import com.google.gson.annotations.SerializedName

data class FilmResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("poster")
    val posters: Poster?,
    @SerializedName("rating")
    val rating: FilmRating?,
    /*  @SerializedName("feedback")
      val feedback: String?,*/
    /*    @SerializedName("poster")
        val poster: List<String>?,*/

    )


/*    rating": {
"kp": 6.2,
"imdb": 8.4,
"tmdb": 3.2,
"filmCritics": 10,
"russianFilmCritics": 5.1,
"await": 6.1
},*/

/*

poster
"poster": {
    "url": "string",
    "previewUrl": "string"
},

*/


