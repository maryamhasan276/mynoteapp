package com.tecra.cloverly

import android.content.Context
import android.content.SharedPreferences

class WSharedPreferences(fileName: String, context: Context) {

    private val fileName: String
    private val context: Context
    private val defValueLong: Long = 0
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var editor: SharedPreferences.Editor
    fun writeLong(key: String?, value: Long) {
        editor.putLong(key, value).commit()
    }

    fun readLong(key: String?): Long {
        return sharedPreferences.getLong(key, defValueLong)
    }

    fun writeString(key: String?, value: String?) {
        editor.putString(key, value).commit()
    }

    fun readString(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun writeBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

    fun readInt(key: String?): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun writeInt(key: String?, value: Int) {
        editor.putInt(key, value).commit()
    }

    fun readBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun clearAllData() {
        sharedPreferences.edit().clear().commit()
    }

    companion object {
        const val MAIN_FILENAME = "w_cinema_app_pref_data"
        const val IS_LOGIN = "is_login"
        const val LOGGED_USER_ID = "logged_user_id"
        const val IS_FIRST_LAUNCH = "is_first_launch"
        const val APP_LANGUAGE = "app_language"
    }

    init {
        editor = sharedPreferences.edit()
        this.fileName = fileName
        this.context = context
    }
}