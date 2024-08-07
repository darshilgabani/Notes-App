package com.biz.notesapp.ui.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.biz.notesapp.R
import com.biz.notesapp.model.Notes
import com.biz.notesapp.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class EditNotesFragment : Fragment() {
    val notesData by navArgs<EditNotesFragmentArgs>()

    val viewModel: NotesViewModel by viewModels()


    lateinit var titleEditText: EditText
    lateinit var subTitleEditText: EditText
    lateinit var notesEditText: EditText
    lateinit var editSaveButton: FloatingActionButton
    lateinit var tagRed: ImageView
    lateinit var tagYellow: ImageView
    lateinit var tagGreen: ImageView

    var priority = "1"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_notes, container, false)

        initVar(view)

        setData()

        onClick()

        return view
    }

    private fun onClick() {

        editSaveButton.setOnClickListener(View.OnClickListener {
            val title = titleEditText.text.toString()
            val subtitle = subTitleEditText.text.toString()
            val notes = notesEditText.text.toString()
            val date: CharSequence =
                android.text.format.DateFormat.format("MMMM d, yyyy ", Date().time)

            val data = Notes(notesData.id, title, subtitle, notes, date.toString(), priority)
            viewModel.updateNotes(data)

            Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

            Navigation.findNavController(it).navigate(R.id.action_editNotesFragment_to_homeFragment)
        })

        tagRed.setOnClickListener(View.OnClickListener {
            priority = "1"
            tagRed.setImageResource(R.drawable.ic_baseline_done_24)
            tagYellow.setImageResource(0)
            tagGreen.setImageResource(0)
        })

        tagYellow.setOnClickListener(View.OnClickListener {
            priority = "2"
            tagYellow.setImageResource(R.drawable.ic_baseline_done_24)
            tagRed.setImageResource(0)
            tagGreen.setImageResource(0)
        })

        tagGreen.setOnClickListener(View.OnClickListener {
            priority = "3"
            tagGreen.setImageResource(R.drawable.ic_baseline_done_24)
            tagYellow.setImageResource(0)
            tagRed.setImageResource(0)
        })
    }

    private fun setData() {
        titleEditText.setText(notesData.title)
        subTitleEditText.setText(notesData.subtitle)
        notesEditText.setText(notesData.notes)

        when (notesData.priority) {
            "1" -> {
                priority = "1"
                tagRed.setImageResource(R.drawable.ic_baseline_done_24)
                tagYellow.setImageResource(0)
                tagGreen.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                tagYellow.setImageResource(R.drawable.ic_baseline_done_24)
                tagRed.setImageResource(0)
                tagGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                tagGreen.setImageResource(R.drawable.ic_baseline_done_24)
                tagYellow.setImageResource(0)
                tagRed.setImageResource(0)
            }
        }

    }

    private fun initVar(view: View) {
        titleEditText = view.findViewById(R.id.titleEditText)
        subTitleEditText = view.findViewById(R.id.subTitleEditText)
        notesEditText = view.findViewById(R.id.notesEditText)

        editSaveButton = view.findViewById(R.id.editSaveButton)

        tagRed = view.findViewById(R.id.tagRed)
        tagYellow = view.findViewById(R.id.tagYellow)
        tagGreen = view.findViewById(R.id.tagGreen)

    }

}