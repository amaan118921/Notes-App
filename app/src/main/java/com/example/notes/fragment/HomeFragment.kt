package com.example.notes.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.adapter.HomeAdapter
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.Item
import com.example.notes.viewModel.NoteViewModel
import com.example.notes.viewModel.NoteViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val model: NoteViewModel by activityViewModels{
        NoteViewModelFactory(
            (activity?.application as com.example.notes.application.Application).database.itemDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = HomeAdapter(model, requireParentFragment())
        binding.homeRecyclerView.adapter = adapter

        model.allItems.observe(this.viewLifecycleOwner) { item ->
            item.let {
                adapter.submitList(it)
            }
        }

        binding.add.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_noteFragment) }

    }
}