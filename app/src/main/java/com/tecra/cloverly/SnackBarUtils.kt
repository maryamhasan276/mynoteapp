package com.tecra.cloverly

import android.R
import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

/**
 * Created by wisleem on 2/24/19.
 */
class SnackBarUtils(
    private val TEXT: String,
    private val BACKGROUND_COLOR: Int,
    private val TEXT_COLOR: Int,
    private val BUTTON_COLOR: Int
) {
    fun snackBar(activity: Activity): Snackbar {
        val snackie = Snackbar.make(
            activity.findViewById(R.id.content),
            TEXT,
            Snackbar.LENGTH_LONG
        )
        val snackView = snackie.view
        val snackViewText =
            snackView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        val snackViewButton =
            snackView.findViewById<View>(com.google.android.material.R.id.snackbar_action) as Button
        snackView.setBackgroundColor(BACKGROUND_COLOR)
        snackViewText.setTextColor(TEXT_COLOR)
        snackViewButton.setTextColor(BUTTON_COLOR)
        return snackie
    }

}