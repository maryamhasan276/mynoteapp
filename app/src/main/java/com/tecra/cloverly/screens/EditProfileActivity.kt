package com.tecra.cloverly.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tecra.cloverly.APP
import com.tecra.cloverly.R
import com.tecra.cloverly.SnackBarUtils
import com.tecra.cloverly.WSharedPreferences
import com.tecra.cloverly.database.NotesDatabase
import com.tecra.cloverly.model.User

import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.eTxtEmail
import kotlinx.android.synthetic.main.activity_edit_profile.eTxtPassword

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val loggedUser = NotesDatabase(this,isReadable = true).getUser(
            APP.instance!!.getSharedPreferences().readInt(WSharedPreferences.LOGGED_USER_ID)
        )

        Log.e("user", loggedUser.toString())

        showLoggedUserData(loggedUser)

    }


    private fun makeListeners(loggedUser: User) {

        imgBack.setOnClickListener { onBackPressed() }

        layout_change_avatar.setOnClickListener {
//            makeSnackBar("Soon")
        }

        btnUpdateProfile.setOnClickListener {
            if (eTxtName.text.toString().trim() == "") {
//                makeSnackBar("Enter Your Name")
            } else {
                if (eTxtUsername.text.toString().trim() == "") {
                    makeSnackBar("Enter Your Username")
                } else {
                    if (eTxtEmail.text.toString().trim() == "") {
                        makeSnackBar("Enter Your Email")
                    } else {
                        if (eTxtPassword.text.toString().trim() == "") {
                            makeSnackBar("Enter Your Password")
                        } else {
                            updateUserData(
                                User(
                                    loggedUser.id,
                                    eTxtName.text.toString().trim(),
                                    eTxtUsername.text.toString().trim(),
                                    eTxtEmail.text.toString().trim(),
                                    eTxtPassword.text.toString().trim()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    fun makeSnackBar(str: String) {
        SnackBarUtils(
            str,
            resources.getColor(R.color.colorPrimary),
            Color.WHITE,
            resources.getColor(R.color.colorAccent)
        ).snackBar(this).show()
    }
    private fun showLoggedUserData(loggedUser: User) {

        if (loggedUser == null) {
            finish()
        } else {

            eTxtName.setText(loggedUser.fullName)
            eTxtUsername.setText(loggedUser.userName)
            eTxtEmail.setText(loggedUser.email)
            eTxtPassword.setText(loggedUser.password)

            makeListeners(loggedUser)

        }

    }

    private fun updateUserData(user: User) {
        if (NotesDatabase(this,isReadable = false).updateUser(user)) {
            makeToast("Update Successful!")
            finish()
        } else {
            makeToast("Update Failed, Try Again later!")
        }
    }
    fun makeToast(str: String?) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}