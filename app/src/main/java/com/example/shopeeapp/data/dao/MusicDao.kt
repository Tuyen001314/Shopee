package com.example.shopeeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopeeapp.data.enitity.SongEntity

@Dao
interface MusicDao {

    @Query("select * from MusicDB")
    suspend fun getAllSong(): List<SongEntity>

    @Insert
    suspend fun insert(song: SongEntity)
}