package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private var isLinearLayoutManager = true

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (bool) to opposite value
                isLinearLayoutManager = !isLinearLayoutManager

                //Sets layout + icon
                chooseLayout()
                setIcon(item)

                return true
            }
            // Otherwise, do nothing and use the core event handling
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager)
            recyclerView.layoutManager = LinearLayoutManager(context)
        else
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem != null) {
            menuItem.icon =
                if (isLinearLayoutManager)
                    ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout) // requireContext() = getContext()!!
                else
                    ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
        }
    }

}