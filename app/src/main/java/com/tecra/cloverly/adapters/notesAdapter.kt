package com.tecra.cloverly.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tecra.cloverly.R
import com.tecra.cloverly.model.note
import kotlinx.android.synthetic.main.item_note.view.*
import com.tecra.cloverly.screens.Note_Editor
import com.tecra.cloverly.database.NotesDatabase

class notesAdapter (val context : Context,val notes : ArrayList<note>) :
    RecyclerView.Adapter<notesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext());
              val view =(layoutInflater.inflate(R.layout.item_note, parent, false));


            return ViewHolder(view)

    }


    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(notes[position])
        holder.itemView.item_card.setOnClickListener {
            val intent = Intent(this.context,
                Note_Editor::class.java)
            val list = notes[position]
            val id = list.id
            val color = list.color
            val title =list.title
            val des = list.des
            val date = list.date
            val type = list.type

            intent.putExtra("id",id)
            intent.putExtra("color",color)
            intent.putExtra("title",title)
            intent.putExtra("des",des)
            intent.putExtra("date",date)
            intent.putExtra("type",type)

            startActivity(context,intent,null);

        }

        holder.itemView.item_card.setOnLongClickListener {
            val list = notes[position]
            val bioHelper = NotesDatabase(this.context,isReadable = false)
            val selectionArgs = arrayOf(list.id.toString())
            bioHelper.Delete("ID=?",selectionArgs)
            Toast.makeText(this.context,"Note is deleted " , Toast.LENGTH_SHORT ).show()
            true
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun bindItems(list: note){
            itemView.title.text = list.title
            itemView.sub.text = list.des

        if (list.color == "green"){
            itemView.item_card.setBackgroundResource(R.color.green)
        }else if(list.color == "red"){
            itemView.item_card.setBackgroundResource(R.color.red)
        }else if (list.color == "yellow"){
            itemView.item_card.setBackgroundResource(R.color.yellow)
        }else if (list.color  == "black" ){
            itemView.item_card.setBackgroundResource(R.color.white)
        } else if (list.color  == "blue" ){
            itemView.item_card.setBackgroundResource(R.color.blue)
        }else if (list.color  == "white" ){
            itemView.item_card.setBackgroundResource(R.color.white)
        }else if (list.color  == "pink" ){
            itemView.item_card.setBackgroundResource(R.color.pink)
        }else if (list.color  == "brown" ){
            itemView.item_card.setBackgroundResource(R.color.brown)
        }
            if (list.title == "green"){
                itemView.title.setTextColor(R.color.green)
            }else if(list.color == "red"){
                itemView.title.setTextColor(R.color.red)
            }else if (list.color == "yellow"){
                itemView.title.setTextColor(R.color.yellow)
            }else if (list.color  == "black" ){
                itemView.title.setTextColor(R.color.black)
            } else if (list.color  == "blue" ){
                itemView.title.setTextColor(R.color.blue)
            }else if (list.color  == "white" ){
                itemView.title.setTextColor(R.color.white)
            }else if (list.color  == "pink" ){
                itemView.title.setTextColor(R.color.pink)
            }else if (list.color  == "brown" ){
                itemView.title.setTextColor(R.color.brown)
            }



        }
    }
}