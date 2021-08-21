package com.example.notes.viewModel

import androidx.lifecycle.*
import androidx.room.Dao
import com.example.notes.model.Item
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class NoteViewModel(private val itemDao: com.example.notes.Dao): ViewModel() {

    private lateinit var oldItem: Item

    fun getItemForFragment(): LiveData<Item> {
        var item : LiveData<Item> = itemDao.getItem(oldItem.key).asLiveData()
        return item
    }

    fun setItem(item: Item) {
        oldItem = item
    }
    fun getItem(): Item {
        return oldItem
    }

    fun getTime(): String {
        val currentTime: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("dd MMM", Locale.getDefault())
        val fD = df.format(currentTime)
        return fD.toString()
    }

    fun deleteItem(item: Item) {
        delete(item)
    }
    private fun delete(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun getItemForUpdate(key: Int, title: String, body: String, time: String) {
        val updatedItem = Item(key = key, title = title, body = body, time = time)
        updateItem(updatedItem)
    }
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }
    private lateinit var title: String
    private lateinit var body: String

    fun setTitle(title: String) {
        this.title = title
    }
    val allItems: MutableLiveData<MutableList<Item>> = itemDao.getAllItems().asLiveData() as MutableLiveData<MutableList<Item>>
    fun setBody(body: String) {
        this.body = body
    }
    fun getBody(): String {
        return body
    }
    fun getTitle(): String {
        return title
    }

    fun getNewItem(title: String, body: String, time: String) {
        val item = Item(title = title, body = body, time = time)
        insertItem(item)
    }
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

}


class NoteViewModelFactory(private val itemDao: com.example.notes.Dao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}