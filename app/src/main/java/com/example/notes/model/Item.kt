package com.example.notes.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val key: Int = 0,
    @ColumnInfo(name="title") @NonNull val title: String,
    @ColumnInfo(name="body") @NonNull val body: String,
    @ColumnInfo(name="time") @NonNull val time: String
    )