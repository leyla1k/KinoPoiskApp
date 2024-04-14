package com.example.android_task.localdata

import androidx.room.TypeConverter
import com.example.android_task.model.simple.Poster
import org.json.JSONObject


class LocalDBConverter {
    @TypeConverter
    fun fromString(value: String?): Poster? {
        return value?.let {
            val jsonObject = JSONObject(it)
            return Poster(jsonObject.getString("url"), jsonObject.getString("previewUrl"))
        }
    }

    @TypeConverter
    fun posterToString(poster: Poster?): String? {
        return poster?.let {
            val jsonObject = JSONObject()
            jsonObject.put("url", poster.url)
            jsonObject.put("previewUrl", poster.previewUrl)
            return jsonObject.toString()
        }
    }
}