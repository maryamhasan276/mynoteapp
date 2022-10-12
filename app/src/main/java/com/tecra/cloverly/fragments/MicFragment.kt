package com.tecra.cloverly.fragments
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import com.tecra.cloverly.screens.Recording
import com.tecra.cloverly.adapters.RecordingAdapter

import com.tecra.cloverly.screens.Audio_Editor
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecra.cloverly.R
import kotlinx.android.synthetic.main.item_recording.view.*
import java.io.File
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class MicFragment : Fragment(), RecordingAdapter.OnClickListener {
    private var mediaPlayer: MediaPlayer? = null
    private var lastProgress = 0
    private val mHandler = Handler()
    private var isPlaying = false
    private var last_index = -1
    var myAdapter: RecordingAdapter? = null
    val recordArrayList = ArrayList<Recording>()


    override fun onClickPlay(view: View, record: Recording, recordingList: ArrayList<Recording>, position: Int) {
        playRecordItem(view, record, recordingList, position)
    }

    private fun playRecordItem(view: View, recordItem: Recording, recordingList: ArrayList<Recording>, position: Int) {
        val recording = recordingList[position]

        if (isPlaying) {
            stopPlaying()
            if (position == last_index) {
                recording.isPlaying = false
                stopPlaying()
                myAdapter!!.notifyItemChanged(position)
            } else {
                markAllPaused(recordingList)
                recording.isPlaying = true
                myAdapter!!.notifyItemChanged(position)
                startPlaying(recording.uri, recordItem, view.seekBar, position)
                last_index = position
            }
            seekUpdate(view)
        } else {
            if (recording.isPlaying!!) {
                recording.isPlaying = false
                stopPlaying()
            } else {
                startPlaying(recording.uri, recordItem, view.seekBar, position)
                recording.isPlaying = true
                view.seekBar.max = mediaPlayer!!.duration
            }
            myAdapter!!.notifyItemChanged(position)
            last_index = position
        }

        manageSeekBar(view.seekBar)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val v = inflater.inflate(R.layout.fragment_mic, container, false)
            val fab = v.findViewById<View>(R.id.fab2) as FloatingActionButton
            fab.setOnClickListener {
                val intent = Intent(this.context,Audio_Editor::class.java)
                startActivity(intent)
            }
            val recyclrView = v.findViewById<View>(R.id.er) as RecyclerView
            val recordArrayList = ArrayList<Recording>()
            val root = android.os.Environment.getExternalStorageDirectory()
            val path = root.absolutePath + "/Cloverly/Audios"
            val directory = File(path)
            val files = directory.listFiles()
            if (files != null) {

                for (i in files.indices) {
                    val fileName = files[i].name
                    val recordingUri = root.absolutePath + "/Cloverly/Audios/" + fileName
                    recordArrayList.add(
                        Recording(
                            recordingUri,
                            fileName,
                            false
                        )
                    )
                }
                recyclrView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                myAdapter = RecordingAdapter(recordArrayList)
                myAdapter!!.setListener(this)
                recyclrView.adapter = myAdapter
            }

        return v
    }

    private fun seekUpdate(itemView: View) {
        if (mediaPlayer != null) {
            val mCurrentPosition = mediaPlayer!!.currentPosition
            itemView.seekBar.max = mediaPlayer!!.duration
            itemView.seekBar.progress = mCurrentPosition
            lastProgress = mCurrentPosition
        }
        mHandler.postDelayed(Runnable { seekUpdate(itemView) }, 100)
    }

    private fun manageSeekBar(seekBar: SeekBar?) {
        seekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer!!.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun stopPlaying() {
        try {
            mediaPlayer!!.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mediaPlayer = null
        isPlaying = false
    }

    private fun startPlaying(uri: String?, audio: Recording?, seekBar: SeekBar?, position: Int) {
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource(uri)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            Log.e("LOG_TAG", "prepare() failed")
        }
        //showing the pause button
        seekBar!!.max = mediaPlayer!!.duration
        isPlaying = true

        mediaPlayer!!.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            audio!!.isPlaying = false
            myAdapter!!.notifyItemChanged(position)
        })
    }

    private fun markAllPaused(recordingList: ArrayList<Recording>) {
        for (i in recordingList.indices) {
            recordingList[i].isPlaying = false
            recordingList[i] = recordingList[i]
        }
        myAdapter!!.notifyDataSetChanged()
    }



}
