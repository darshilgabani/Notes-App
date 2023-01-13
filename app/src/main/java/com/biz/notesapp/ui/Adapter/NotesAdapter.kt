package com.biz.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.biz.notesapp.Interface.SelectListener
import com.biz.notesapp.R
import com.biz.notesapp.model.Notes
import com.biz.notesapp.ui.Fragment.HomeFragmentArgs
import com.biz.notesapp.ui.Fragment.HomeFragmentDirections
import com.biz.notesapp.viewmodel.NotesViewModel

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    var onLongClick: SelectListener? = null

    fun setLongSelectListener(onLongClick: SelectListener) {
        this.onLongClick = onLongClick
    }

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    inner class notesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewDot = itemView.findViewById<View>(R.id.viewDot)
        var editButton = itemView.findViewById<View>(R.id.editButton)
        var notesTitle = itemView.findViewById<TextView>(R.id.notesTitle)
        var notesSubTitle = itemView.findViewById<TextView>(R.id.notesSubTitle)
        var notesDate = itemView.findViewById<TextView>(R.id.notesDate)
        var cardView = itemView.findViewById<CardView>(R.id.cardView)

        fun onBindData(data: Notes) {
            notesDate.text = data.date
            notesTitle.text = data.title
            notesSubTitle.text = data.subTitle

            when (data.priority) {
                "1" -> {
                    viewDot.setBackgroundResource(R.drawable.dot_red)
                }
                "2" -> {
                    viewDot.setBackgroundResource(R.drawable.dot_yellow)
                }
                "3" -> {
                    viewDot.setBackgroundResource(R.drawable.dot_green)
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        val view = LayoutInflater.from(requireContext).inflate(R.layout.item_notes, parent, false)
        return notesViewHolder(view)
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.onBindData(data)

        holder.cardView.setOnClickListener(View.OnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToViewNotesFragment(
                data.title,
                data.subTitle,
                data.priority,
                data.notes,
                data.id!!
            )
            Navigation.findNavController(it).navigate(action)
        })

        holder.editButton.setOnClickListener(View.OnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(
                data.title,
                data.subTitle,
                data.priority,
                data.notes,
                data.id!!
            )
            Navigation.findNavController(it).navigate(action)
        })

        holder.cardView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                onLongClick?.onLongClicked(data)
                Toast.makeText(requireContext, "Deleted Notes Successfully", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
        })

    }


    override fun getItemCount() = notesList.size

}