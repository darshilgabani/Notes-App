package com.biz.notesapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.biz.notesapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ViewNotesFragment : Fragment() {
    val notesData by navArgs<ViewNotesFragmentArgs>()

    lateinit var titleTextView: TextView
    lateinit var subTitleTextView: TextView
    lateinit var notesTextView: TextView
    lateinit var tagDot: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_notes, container, false)

        initVar(view)

        setData()

        return view
    }

    private fun setData() {
        titleTextView.text = notesData.title
        subTitleTextView.text = notesData.subtitle
        notesTextView.text = notesData.notes

        when (notesData.priority) {
            "1" -> {
                tagDot.setBackgroundResource(R.drawable.dot_red)
            }
            "2" -> {
                tagDot.setBackgroundResource(R.drawable.dot_yellow)
            }
            "3" -> {
                tagDot.setBackgroundResource(R.drawable.dot_green)
            }
        }
    }

    private fun initVar(view: View) {
        titleTextView = view.findViewById(R.id.titleTextView)
        subTitleTextView = view.findViewById(R.id.subTitleTextView)
        notesTextView = view.findViewById(R.id.notesTextView)
        tagDot = view.findViewById(R.id.tagDot)
    }




}