package com.example.notes

import androidx.room.*
import androidx.room.Dao
import com.example.notes.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM Item ORDER BY title ASC")
    fun getAllItems(): Flow<MutableList<Item>>

    @Query("SELECT * from Item WHERE `key` = :id ")
    fun getItem(id: Int): Flow<Item>

    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)


}