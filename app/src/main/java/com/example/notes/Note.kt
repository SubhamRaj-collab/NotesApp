package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
class Note (
    @ColumnInfo(name = "text") val text: String,
    @PrimaryKey(autoGenerate = true)val id: Int = 0
)