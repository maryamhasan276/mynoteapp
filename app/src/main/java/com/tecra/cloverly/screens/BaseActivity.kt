package com.tecra.cloverly.screens

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.LocaleList
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tecra.cloverly.R
import com.tecra.cloverly.SnackBarUtils
import java.util.*

/**
 * Created by wisleem on 8/12/18.
 */
open class BaseActivity : AppCompatActivity() {

    /*
    do you have actual design?
     */
    override fun attachBaseContext(newBase: Context) {
        var newBase = newBase
        val res = newBase.resources
        val configuration = res.configuration

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            super.attachBaseContext(newBase)
        } else {
            super.attachBaseContext(newBase)
        }
    }

    fun makeToast(str: String?) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    fun getStrFromR(id: Int): String {
        return resources.getString(id)
    }

    fun makeSnackBar(str: String) {
        SnackBarUtils(
            str,
            resources.getColor(R.color.colorPrimary),
            Color.WHITE,
            resources.getColor(R.color.colorAccent)
        ).snackBar(this).show()
    }

    @ColorInt
    fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(this, res)
    }

    fun updateLabel(txt: TextView, day: Int, month: Int, year: Int) {
        var finalDay = day.toString()
        var finalMonth = getTrueMonth(month).toString()
        if (day < 10) {
            finalDay = "0$day"
        }
        if (getTrueMonth(month) < 10) {
            finalMonth = "0$finalMonth"
        }
        txt.text = "$year-$finalMonth-$finalDay"
    }

    fun getDate(day: Int, month: Int, year: Int): String {
        var finalDay = day.toString()
        var finalMonth = getTrueMonth(month).toString()
        if (day < 10) {
            finalDay = "0$day"
        }
        if (getTrueMonth(month) < 10) {
            finalMonth = "0$finalMonth"
        }
        return "$year-$finalMonth-$finalDay"
    }

    companion object {
        fun getTrueMonth(calMonth: Int): Int {
            if (calMonth == 1) return 2 else if (calMonth == 2) return 3 else if (calMonth == 3) return 4 else if (calMonth == 4) return 5 else if (calMonth == 5) return 6 else if (calMonth == 6) return 7 else if (calMonth == 7) return 8 else if (calMonth == 8) return 9 else if (calMonth == 9) return 10 else if (calMonth == 10) return 11 else if (calMonth == 11) return 12
            return 1
        }
    }
}