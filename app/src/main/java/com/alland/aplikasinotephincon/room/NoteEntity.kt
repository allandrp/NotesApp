package com.alland.aplikasinotephincon.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "created_date")
    var createdDate: Long,

    @ColumnInfo(name = "last_updated")
    var lastUpdated: Long,

    @ColumnInfo(name = "content")
    var content: String? = null
): Parcelable

