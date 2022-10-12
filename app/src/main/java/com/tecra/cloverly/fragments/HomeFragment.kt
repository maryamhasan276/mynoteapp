package com.tecra.cloverly.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecra.cloverly.R
import com.tecra.cloverly.model.note
import com.tecra.cloverly.adapters.HomeAdapter
import com.tecra.cloverly.database.NotesDatabase
import com.tecra.cloverly.screens.Note_Editor
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    val notesList = ArrayList<note>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

            val view = inflater.inflate(R.layout.fragment_home, container, false)
        //Animations



            LoadBioQuery("%")

            val recyclerView = view.findViewById<View>(R.id.recycler_1) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
            val adapter = HomeAdapter(this.context!!, notesList)
            recyclerView.adapter = adapter
        return  view

    }



    private fun LoadBioQuery (name: String){
        val bioHelp = NotesDatabase(this.context!!,isReadable = true)
        val projection = arrayOf("ID","Color","Title","Description","Date","Type")
        val selectionArgs = arrayOf(name)
        val cursor = bioHelp.Query(projection,"Title like ?", selectionArgs, "Title")
        notesList.clear()
        if (cursor.moveToFirst()){
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Color = cursor.getString(cursor.getColumnIndex("Color"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))
                val Date = cursor.getString(cursor.getColumnIndex("Date"))
                val Type = cursor.getString(cursor.getColumnIndex("Type"))

                notesList.add(note(ID,Color,Title,Description,Date,Type))
            }while (cursor.moveToNext())
        }
    }

    override fun onStart() {
        super.onStart()


    }
}
