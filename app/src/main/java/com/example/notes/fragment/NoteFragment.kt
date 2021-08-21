package com.example.notes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.application.Application
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.viewModel.NoteViewModel
import com.example.notes.viewModel.NoteViewModelFactory


class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private val model: NoteViewModel by activityViewModels {
        NoteViewModelFactory(
            (activity?.application as Application).database.itemDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.done.setOnClickListener {

            val title = binding.titleNote.text.toString()
            val body = binding.bodyNote.text.toString()
            if(title.isBlank() || body.isBlank()) {

            }
            else {
                model.getNewItem(title, body, model.getTime())
                findNavController().navigate(R.id.action_noteFragment_to_homeFragment)
            }

        }
    }
}