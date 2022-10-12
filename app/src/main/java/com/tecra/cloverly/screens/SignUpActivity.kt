package com.tecra.cloverly.screens

import android.os.Bundle
import android.os.CountDownTimer
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import com.leo.simplearcloader.SimpleArcLoader
import com.tecra.cloverly.R
import com.tecra.cloverly.database.NotesDatabase
import com.tecra.cloverly.model.User
import com.tecra.cloverly.screens.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initViews()
    }

    private fun initViews() {
        focus_thief.requestFocus()

        makeListeners()

    }

    private fun makeListeners() {

        txtUserPolicy.setOnClickListener {
            makeSnackBar("User Policy")
        }

        txtTermsConditions.setOnClickListener {
            makeSnackBar("Terms Conditions")
        }

        btnSignUp.setOnClickListener {
            if (eTxtFirstName.text.toString().trim() == "") {
                makeSnackBar("Please Enter First Name")
            } else {
                if (eTxtLastName.text.toString().trim() == "") {
                    makeSnackBar("Please Enter Last Name")
                } else {
                    if (eTxtUserName.text.toString().trim() == "") {
                        makeSnackBar("Please Enter Username")
                    } else {
                        if (eTxtEmail.text.toString().trim() == "") {
                            makeSnackBar("Please Enter Email")
                        } else {
                            if (eTxtPassword.text.toString().trim() == "") {
                                makeSnackBar("Please Enter Password")
                            } else {
                                if (eTxtConfirmPassword.text.toString().trim() == "") {
                                    makeSnackBar("Please Enter Password Again")
                                } else {
                                    if (eTxtPassword.text.toString().trim() != eTxtConfirmPassword.text.toString().trim()) {
                                        makeSnackBar("Passwords did not match")
                                    } else {
                                        signUpRequest()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        btnSignUp.setOnLongClickListener {
            eTxtFirstName.setText("Wagdy")
            eTxtLastName.setText("Isleem")
            eTxtUserName.setText("wisleem")
            eTxtEmail.setText("wbisleem@gmail.com")
            eTxtPassword.setText("123123")
            eTxtConfirmPassword.setText("123123")
            true
        }

    }


    fun signUpRequest() {

        var mDialog = SimpleArcDialog(this)
        var configuration = ArcConfiguration(this)
        configuration.loaderStyle = SimpleArcLoader.STYLE.SIMPLE_ARC
        configuration.text = "please_wait"
        configuration.animationSpeed = 8
        configuration.textSize = 15
        configuration.colors = intArrayOf(
            resources.getColor(R.color.colorAccent),
            resources.getColor(R.color.colorPrimary)
        )
        mDialog.setConfiguration(configuration)
        mDialog.setCancelable(false)
        mDialog.show()

        object : CountDownTimer(1250, 50) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                mDialog.dismiss()
                if (NotesDatabase(this@SignUpActivity,isReadable = false).insertUser(
                        User(
                            0,
                            eTxtFirstName.text.toString().trim() + " " + eTxtLastName.text.toString().trim(),
                            eTxtUserName.text.toString().trim() + "",
                            eTxtEmail.text.toString().trim() + "",
                            eTxtPassword.text.toString().trim()
                        )
                    )
                ) {
                    makeToast("SignUp Successful, Please Login!")
                    finish()
                } else {
                    makeToast("SignUp Failed, Try Again later!")
                }
            }
        }.start()

    }
}