package com.biz.notesapp.ui.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.biz.notesapp.Interface.SelectListener
import com.biz.notesapp.R
import com.biz.notesapp.model.Notes
import com.biz.notesapp.ui.Adapter.NotesAdapter
import com.biz.notesapp.viewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment(),SelectListener {
    lateinit var recyclerView: RecyclerView
    lateinit var addNoteButton: FloatingActionButton
    lateinit var tagRed: ImageView
    lateinit var tagYellow: ImageView
    lateinit var tagGreen: ImageView
    lateinit var filterButton: ImageView


    var oldMyNotes = arrayListOf<Notes>()
    val viewModel: NotesViewModel by viewModels()

    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initVar(view)

        setLayoutAndAdapter()

        onClick()

        return view
    }

    private fun setLayoutAndAdapter() {
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            recyclerView.layoutManager = StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(), notesList)
            adapter.setLongSelectListener(this)
            recyclerView.adapter = adapter
        }
    }

    private fun onClick() {
        addNoteButton.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        })


        filterButton.setOnClickListener(View.OnClickListener {
            filterButton.setImageResource(R.drawable.filter_on)
            tagGreen.setImageResource(0)
            tagYellow.setImageResource(0)
            tagRed.setImageResource(0)
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                recyclerView.layoutManager = StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                adapter.setLongSelectListener(this)
                recyclerView.adapter = adapter
            }
        })

        tagGreen.setOnClickListener(View.OnClickListener {
            filterButton.setImageResource(R.drawable.filter_off)
            tagGreen.setImageResource(R.drawable.ic_baseline_done_24)
            tagYellow.setImageResource(0)
            tagRed.setImageResource(0)
            viewModel.getGreenDotNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                recyclerView.layoutManager = StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                adapter.setLongSelectListener(this)
                recyclerView.adapter = adapter
            }
        })

        tagYellow.setOnClickListener(View.OnClickListener {
            filterButton.setImageResource(R.drawable.filter_off)
            tagYellow.setImageResource(R.drawable.ic_baseline_done_24)
            tagRed.setImageResource(0)
            tagGreen.setImageResource(0)
            viewModel.getYellowDotNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                recyclerView.layoutManager = StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                adapter.setLongSelectListener(this)
                recyclerView.adapter = adapter
            }
        })

        tagRed.setOnClickListener(View.OnClickListener {
            filterButton.setImageResource(R.drawable.filter_off)
            tagRed.setImageResource(R.drawable.ic_baseline_done_24)
            tagYellow.setImageResource(0)
            tagGreen.setImageResource(0)
            viewModel.getRedDotNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                recyclerView.layoutManager = StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                adapter.setLongSelectListener(this)
                recyclerView.adapter = adapter
            }
        })

    }

    private fun initVar(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        addNoteButton = view.findViewById<FloatingActionButton>(R.id.addNoteButton)
        tagGreen = view.findViewById<ImageView>(R.id.tagGreen)
        tagYellow = view.findViewById<ImageView>(R.id.tagYellow)
        tagRed = view.findViewById<ImageView>(R.id.tagRed)
        filterButton = view.findViewById<ImageView>(R.id.filterButton)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.seachMenu)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newFilteredList = arrayListOf<Notes>()

                for (i in oldMyNotes){
                    if (i.title.contains(newText!!) || i.subTitle.contains(newText!!)){
                        newFilteredList.add(i)
                    }
                }

                adapter.filtering(newFilteredList)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onLongClicked(idData: Notes) {
        viewModel.deleteNotes(idData.id!!)
    }

}