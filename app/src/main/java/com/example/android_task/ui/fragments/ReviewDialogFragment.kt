package com.example.android_task.ui.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.android_task.R


class ReviewDialogFragment : DialogFragment() {

    private val review: String by lazy {
        requireArguments().getString("review")
            ?: throw IllegalArgumentException("Review argument is missing")
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Полный отзыв")
            val customLayout: View =
                layoutInflater.inflate(R.layout.alert_dialog_review, null)
            builder.setView(customLayout)
            val textView =
                customLayout.findViewById<TextView>(R.id.tv_review)
            textView.text = review
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}