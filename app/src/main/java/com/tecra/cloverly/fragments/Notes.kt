package com.tecra.cloverly.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecra.cloverly.R
import com.tecra.cloverly.model.note
import com.tecra.cloverly.adapters.notesAdapter
import com.tecra.cloverly.database.NotesDatabase
import com.tecra.cloverly.screens.Note_Editor


/**
 * A simple [Fragment] subclass.
 */
class Notes : Fragment() {

    val notesList = ArrayList<note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       LoadBioQuery("%")
        if (notesList.isEmpty()){
            val view = inflater.inflate(R.layout.no_notes, container, false)
            val Button = view.findViewById<View>(R.id.enter) as Button
            Button.setOnClickListener {
                val intent = Intent(this.context,Note_Editor::class.java)
                startActivity(intent)


            }
            return view

        }else{


            val view = inflater.inflate(R.layout.fragment_notes, container, false)
            val fab = view.findViewById<View>(R.id.fab1) as FloatingActionButton
            fab.setOnClickListener {
                val intent = Intent(this.context,Note_Editor::class.java)
                startActivity(intent)


            }
            val recyclerView = view.findViewById<View>(R.id.recycler) as RecyclerView
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            val adapter = notesAdapter(this.context!!, notesList)
            recyclerView.adapter = adapter


            return  view
        }
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
        LoadBioQuery("%")
    }

    override fun onResume() {
        super.onResume()
        LoadBioQuery("%")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        LoadBioQuery("%")
    }
}
