package com.tecra.cloverly.model

data class User(
    var id: Int,
    var fullName: String,
    var userName: String,
    var email: String,
    var password: String
) {



    companion object {
        const val COL_ID = "id"
        const val COL_FULL_NAME = "full_name"
        const val COL_USERNAME = "username"
        const val COL_EMAIL = "email"
        const val COL_PASSWORD = "password"

        const val TABLE_NAME = "Users"
        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                    "$COL_FULL_NAME text not null," +
                    "$COL_USERNAME text not null," +
                    "$COL_EMAIL text not null," +
                    "$COL_PASSWORD text not null)"
    }

    override fun toString(): String {
        return "User(id=$id, fullName='$fullName', userName='$userName', email='$email', password='$password')"
    }
}