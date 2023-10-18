package com.example.shopeeapp.data.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MusicDB")
data class SongEntity(
    val name: String
) {

}