package com.tecra.cloverly.screens

import android.annotation.SuppressLint
import com.tecra.cloverly.database.NotesDatabase
import android.app.Dialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.tecra.cloverly.R
import kotlinx.android.synthetic.main.activity_note__editor.*
import java.text.DateFormat
import java.util.*

class Note_Editor : AppCompatActivity(), View.OnClickListener {

   var color : String = ""
    var newColor : String? = ""
    var type : String = ""
    var date : String = ""
    var  switch : Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note__editor)
        switch = findViewById<View>(R.id.swi) as Switch

        settingUp()
        var new_color = intent.getStringExtra("color")
        if (new_color == null){
            new_color = "white"
        }else {
            color = new_color
        }
        val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.inflateMenu(R.menu.save)
        toolbar.setOnMenuItemClickListener {item: MenuItem? ->
            when(item!!.itemId){
                R.id.save ->{
                    AddNote()
                    true
                }
                else -> false
            }
        }

        val cardd1= findViewById<View>(R.id.color_background) as CardView
        cardd1.setOnClickListener {
            showBackgroundDialog()
        }
    }



    @SuppressLint("ResourceAsColor")
    private fun settingUp() {
        val id = intent.getIntExtra("id",0)
        val color = intent.getStringExtra("color")
        val t =intent.getStringExtra("title")
        val d =intent.getStringExtra("des")
        val s = intent.getStringExtra("type")
        if(s == "true"){
            switch!!.isChecked = true
        }else{
            switch!!.isChecked = false
        }

        val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
        val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
        val card_colors = findViewById<View>(R.id.color_background) as CardView

        if (id == 0){
            datew.setText(d)
        }else{
            val calendar = Calendar.getInstance()
            val current_date : String = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime())
            datew.setText(current_date)
        }


        if (color == "green"){
            toolba.setBackgroundResource(R.color.green)
            toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.green)));
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.green)}
            card_colors.setBackgroundResource(R.color.green)


        }else if(color == "red"){
            toolba.setBackgroundResource(R.color.red)
            toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                R.color.red
            )));
            card_colors.setBackgroundResource(R.color.red)

            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.red)}
        }else if (color == "yellow"){
            toolba.setBackgroundResource(R.color.yellow)
            toolbar.setBackgroundResource(R.color.yellow)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.yellow)}
            card_colors.setBackgroundResource(R.color.yellow)

        }else if (color  == "black" ){
            toolba.setBackgroundResource(R.color.black)
            toolbar.setBackgroundResource(R.color.black)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.black)}
            card_colors.setBackgroundResource(R.color.black)

        } else if (color  == "blue" ){
            toolba.setBackgroundResource(R.color.blue)
            toolbar.setBackgroundResource(R.color.blue)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.blue)}
            card_colors.setBackgroundResource(R.color.blue)

        }else if (color  == "white" ){
            toolba.setBackgroundResource(R.color.green)
            toolbar.setBackgroundResource(R.color.green)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.green)}
            card_colors.setBackgroundResource(R.color.white)

        }else if (color  == "pink" ){
            toolba.setBackgroundResource(R.color.pink)
            toolbar.setBackgroundResource(R.color.pink)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.pink)}
            card_colors.setBackgroundResource(R.color.pink)

        }else if (color  == "brown" ){
            toolba.setBackgroundResource(R.color.brown)
            toolbar.setBackgroundResource(R.color.brown)
            if (Build.VERSION.SDK_INT >= 21) {
                val window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = resources.getColor(R.color.brown)}
            card_colors.setBackgroundResource(R.color.brown)

        }

        val title = findViewById<View>(R.id.title_edit) as EditText
        val des = findViewById<View>(R.id.des_edit) as EditText
        title.setText(t)
        des.setText(d)

    }

    @SuppressLint("WrongViewCast")
    fun showBackgroundDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(R.layout.item_list_color)

        //listColors
        val l1 = dialog.findViewById<View>(R.id.l1) as CardView
        l1.setOnClickListener(this@Note_Editor)
        val l2 = dialog.findViewById<View>(R.id.l2) as CardView
        l2.setOnClickListener(this@Note_Editor)
        val l3 = dialog.findViewById<View>(R.id.l3) as CardView
        l3.setOnClickListener(this@Note_Editor)
        val l4 = dialog.findViewById<View>(R.id.l4) as CardView
        l4.setOnClickListener(this@Note_Editor)
        val l5 = dialog.findViewById<View>(R.id.l5) as CardView
        l5.setOnClickListener(this@Note_Editor)
        val l6 = dialog.findViewById<View>(R.id.l6) as CardView
        l6.setOnClickListener(this@Note_Editor)
        val l7 = dialog.findViewById<View>(R.id.l7) as CardView
        l7.setOnClickListener(this@Note_Editor)
        val l8 = dialog.findViewById<View>(R.id.l8) as CardView
        l8.setOnClickListener(this@Note_Editor)


        dialog.show()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.l1 ->{
                color = "green"
                Toast.makeText(applicationContext,"set Green",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.green)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.green
                )));
                card_colors.setBackgroundResource(R.color.green)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.green)}
            }
            R.id.l2 ->{
                color = "white"
                Toast.makeText(applicationContext,"set White",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.green)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.green
                )));
                card_colors.setBackgroundResource(R.color.green)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.green)}

            }
            R.id.l3 ->{

                color = "red"
                Toast.makeText(applicationContext,"set Red",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.red)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.red
                )));
                card_colors.setBackgroundResource(R.color.red)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.red)}


            }
            R.id.l4 ->{
                color = "black"
                Toast.makeText(applicationContext,"set Black",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.black)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.black
                )));
                card_colors.setBackgroundResource(R.color.black)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.black)}


            }
            R.id.l5 ->{
                color = "blue"
                Toast.makeText(applicationContext,"set Blue",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.blue)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.blue
                )));
                card_colors.setBackgroundResource(R.color.blue)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.blue)}


            }
            R.id.l6 ->{
                color = "pink"
                Toast.makeText(applicationContext,"set Pink",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.pink)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.pink
                )));
                card_colors.setBackgroundResource(R.color.pink)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.pink)}


            }
            R.id.l7 ->{
                color = "yellow"
                Toast.makeText(applicationContext,"set Red",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.yellow)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.yellow
                )));
                card_colors.setBackgroundResource(R.color.yellow)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.yellow)}



            }
            R.id.l8 ->{
                color = "brown"
                Toast.makeText(applicationContext,"set Brown",Toast.LENGTH_SHORT).show()
                val toolbar = findViewById<View>(R.id.tolbar) as Toolbar
                val toolba = findViewById<View>(R.id.tolbar2) as LinearLayout
                val card_colors = findViewById<View>(R.id.color_background) as CardView
                toolba.setBackgroundResource(R.color.brown)
                toolbar.setBackgroundDrawable(ColorDrawable(getResources().getColor(
                    R.color.brown
                )));
                card_colors.setBackgroundResource(R.color.brown)
                if (Build.VERSION.SDK_INT >= 21) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.statusBarColor = resources.getColor(R.color.brown)}


            }

        }
    }
    private fun AddNote() {
        val id = intent.getIntExtra("id",0)
        val dat = intent.getStringExtra("date")

        val etTitle = findViewById<View>(R.id.title_edit) as EditText
        val etSub = findViewById<View>(R.id.des_edit) as EditText

        if(switch!!.isChecked){
            type = "Private"
        }else{
            type = "Public"

        }
        if (newColor == ""){
             newColor = color
        }else{
            color = newColor!!
        }
        val notesDatabase = NotesDatabase(this,false)
        var values= ContentValues()


        values.put("Color", color.toString())
        values.put("Title",etTitle.text.toString())
        values.put("Description",etSub.text.toString())
        if (id == 0){
            val calendar = Calendar.getInstance()
            val current_date : String = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime())
            date = current_date
        }else{
            date = dat
        }
        values.put("Date",date.toString())
        values.put("Type",type.toString())


        if(id==0) {
            val ID = notesDatabase.Insert(values)
            if (ID > 0) {
                Toast.makeText(applicationContext,"Note is Added",Toast.LENGTH_SHORT).show()
                onBackPressed()
            } else {
                Toast.makeText(applicationContext,"Note is not Added",Toast.LENGTH_SHORT).show()

            }
        }else{
            var selectionArs= arrayOf(id.toString())
            val ID = notesDatabase.Update(values,"ID=?",selectionArs)
            if (ID > 0) {
                Toast.makeText(applicationContext," is updated",Toast.LENGTH_SHORT).show()

                finish()
            } else {
                Toast.makeText(applicationContext," is not update Added",Toast.LENGTH_SHORT).show()

            }
        }
    }
}
