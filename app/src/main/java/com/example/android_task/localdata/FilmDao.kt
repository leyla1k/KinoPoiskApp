package com.example.android_task.localdata

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_task.model.FilmEntity
import com.example.android_task.utils.Global


@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilmsToLocal(films: List<FilmEntity>)

    @Query("SELECT * FROM " + Global.TABLE_NAME)
    fun getFilmsListFromLocal(): PagingSource<Int, FilmEntity>

}