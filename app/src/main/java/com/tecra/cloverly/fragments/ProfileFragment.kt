package com.tecra.cloverly.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.tecra.cloverly.APP
import com.tecra.cloverly.R
import com.tecra.cloverly.WSharedPreferences
import com.tecra.cloverly.database.NotesDatabase
import com.tecra.cloverly.model.User
import com.tecra.cloverly.screens.EditProfileActivity
import com.tecra.cloverly.screens.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loggedUser = NotesDatabase(activity!!,true).getUser(
            APP.instance!!.getSharedPreferences().readInt(WSharedPreferences.LOGGED_USER_ID)
        )

        Log.e("user", loggedUser.toString())

        showLoggedUserData(loggedUser)

    }

    private fun makeListeners() {

        layout_edit_profile.setOnClickListener {
            startActivity(Intent(activity!!, EditProfileActivity::class.java))
        }

        layout_logout.setOnClickListener {

            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView: View =
                inflater.inflate(R.layout.dialog_logout_confirm, null)

            dialog.setContentView(dialogView)

            val btnAction =
                dialogView.findViewById<Button>(R.id.btnAction)

            btnAction.setOnClickListener {
                dialog.dismiss()
                dialog.cancel()

                APP.instance!!.getSharedPreferences().clearAllData()
                val anIntent = Intent(
                    activity!!,
                    LoginActivity::class.java
                )
                anIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(anIntent)

            }
            dialog.window!!.setBackgroundDrawableResource(R.drawable.comments_bg_2)
            dialog.window!!.setWindowAnimations(R.style.dialog_zoom_animation)

            dialog.show()
        }

    }

    override fun onResume() {
        super.onResume()
        showLoggedUserData(
            NotesDatabase(activity!!,isReadable = true).getUser(
                APP.instance!!.getSharedPreferences().readInt(WSharedPreferences.LOGGED_USER_ID)
            )
        )
    }

    private fun showLoggedUserData(loggedUser: User) {
        if (loggedUser == null) {
            activity!!.finish()
        } else {
            txtName.text = loggedUser.fullName
            txtEmail.text = loggedUser.email

            makeListeners()
        }
    }

}

