package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.notes.application.Application
import com.example.notes.databinding.FragmentSingleNoteBinding
import com.example.notes.model.Item
import com.example.notes.viewModel.NoteViewModel
import com.example.notes.viewModel.NoteViewModelFactory


class SingleNoteFragment : Fragment() {

    private lateinit var binding: FragmentSingleNoteBinding
    private lateinit var title: String
    private lateinit var body: String

    private lateinit var item: Item
    private val model: NoteViewModel by activityViewModels{
        NoteViewModelFactory(
            (activity?.application as Application).database.itemDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener { findNavController().navigateUp() }
        item = model.getItem()
        binding.bodyNote.setText(item.body)
        binding.titleNote.setText(item.title)
        binding.done.setOnClickListener {
            title = binding.titleNote.editableText.toString()
            body =   binding.bodyNote.editableText.toString()
            if(title != item.title || body != item.body) {
                model.getItemForUpdate(item.key, title, body, item.time)
                findNavController().navigate(R.id.action_singleNoteFragment_to_homeFragment)
            }
            else {
                findNavController().navigate(R.id.action_singleNoteFragment_to_homeFragment)
            }
        }
    }

}