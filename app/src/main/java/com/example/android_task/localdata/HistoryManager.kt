package com.example.android_task.localdata

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


private const val MAX_HISTORY_SIZE = 20

class HistoryManager(private val sharedPreferences: SharedPreferences, val context: Context) {

    fun addToHistory(query: String) {
        val history = getHistory().toMutableList()
        if (history.contains(query)) {
            history.remove(query)
        }

        history.add(0, query)

        if (history.size > MAX_HISTORY_SIZE) {
            history.subList(MAX_HISTORY_SIZE, history.size).clear()
        }

        val json = Gson().toJson(history)
        sharedPreferences.edit().putString("history", json).apply()
    }

    fun getHistory(): List<String> {
        val jsonString = sharedPreferences.getString("history", null)
        return Gson().fromJson(jsonString, object : TypeToken<List<String>>() {}.type)
            ?: emptyList()
    }

    fun clearHistory() {
        sharedPreferences.edit().remove("history").apply()
    }

}
