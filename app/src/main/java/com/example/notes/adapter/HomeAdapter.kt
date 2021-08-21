package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ItemViewBinding
import com.example.notes.model.Item
import com.example.notes.viewModel.NoteViewModel

class HomeAdapter(private val model: NoteViewModel, private val fragment: Fragment): ListAdapter<Item, HomeAdapter.HomeViewHolder>(DiffCallback) {

    class HomeViewHolder(binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val body = binding.body
        val time = binding.time
        val delete = binding.delete
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val adapterLayout = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)

        holder.title.text = item.title
        holder.body.text = item.body
        holder.time.text = item.time

        holder.delete.setOnClickListener {
            model.deleteItem(item)
        }
        holder.itemView.setOnClickListener {
           model.setItem(item)
            model.setTitle(item.title)
            model.setBody(item.body)
            fragment.findNavController().navigate(R.id.action_homeFragment_to_singleNoteFragment)
        }
    }

}