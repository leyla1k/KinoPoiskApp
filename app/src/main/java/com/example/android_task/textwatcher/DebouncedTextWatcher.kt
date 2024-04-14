package com.example.android_task.textwatcher

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher


class DebouncedTextWatcher(
    private val debouncePeriod: Long,
    private val onTextChanged: (String) -> Unit
) : TextWatcher {

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable { onTextChanged.invoke(s.toString()) }
        handler.postDelayed(runnable!!, debouncePeriod)
    }
}

