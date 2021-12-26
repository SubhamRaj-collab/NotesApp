package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 3)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao(): NoteDao

    companion object{
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE NotesTable ADD COLUMN isCompleted INTEGER NOT NULL DEFAULT(0)")
            }
        }
        val migration_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE NotesTable ADD COLUMN isCompleted INTEGER NOT NULL DEFAULT(0)")
            }
        }
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
//                 return instance
                instance
            }
        }
    }
}