package com.tecra.cloverly.screens

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import com.leo.simplearcloader.SimpleArcLoader
import com.tecra.cloverly.APP
import com.tecra.cloverly.R
import com.tecra.cloverly.WSharedPreferences
import com.tecra.cloverly.database.NotesDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.eTxtPassword
import kotlinx.android.synthetic.main.activity_login.focus_thief

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews() {
        focus_thief.requestFocus()
        btnLogin.setOnLongClickListener {
            eTxtUsername.setText("wisleem")
            eTxtPassword.setText("123123")
            false
        }
        makeListeners()
    }

    private fun makeListeners() {
        btnLogin.setOnClickListener {
            if (eTxtUsername.getText().toString().trim().equals("")) {
                makeSnackBar("Enter username")
            } else {
                if (eTxtPassword.getText().toString().trim().equals("")) {
                    makeSnackBar("Enter password")
                } else {
                    checkLogin(
                        eTxtUsername.getText().toString().trim(),
                        eTxtPassword.getText().toString().trim()
                    )
                }
            }
        }

        txtForgotPassword.setOnClickListener { showForgetPasswordDialog() }

        txtSignUp.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    SignUpActivity::class.java
                )
            )
        }

    }

    fun checkLogin(username: String, password: String) {


        val mDialog = SimpleArcDialog(this)
        val configuration = ArcConfiguration(this)
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
                val user = NotesDatabase(this@LoginActivity,true).checkLogin(username, password)
                if (user != null) {
                    Log.e("user", user.toString())
                    APP.instance!!.getSharedPreferences()
                        .writeBoolean(WSharedPreferences.IS_LOGIN, true)
                    APP.instance!!.getSharedPreferences()
                        .writeInt(WSharedPreferences.LOGGED_USER_ID, user.id)

                    val anIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    anIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(anIntent)

                } else {
                    makeToast("Wrong username or password")
                }
            }
        }.start()


    }

    fun showForgetPasswordDialog() {
        val dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_forget_password, null)
        dialog.setContentView(dialogView)
        val txtTitle = dialogView.findViewById<TextView>(R.id.txtTitle)
        val eTxtEmailAddress = dialogView.findViewById<EditText>(R.id.eTxtEmailAddress)
        val btnSend =
            dialogView.findViewById<Button>(R.id.btnSend)
        btnSend.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window!!.setBackgroundDrawableResource(R.drawable.comments_bg_2)
        dialog.window!!.setWindowAnimations(R.style.dialog_zoom_animation)
        dialog.show()
    }


}