package com.example.android_task.localdata

import android.database.Cursor
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_task.model.FilmEntity
import com.example.android_task.model.simple.Film
import com.example.android_task.ui.vm.FilmsViewModel
import com.example.android_task.utils.Global


@Dao
interface FilmDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilmsToLocal(films: List<FilmEntity>)
    @Query("SELECT * FROM " + Global.TABLE_NAME)
    fun getFilmsListFromLocal(): PagingSource<Int, FilmEntity>

  /*  @Query("SELECT COUNT(*) FROM " + MemoryEntity.TABLE_NAME)
    fun count(): Int


    @Insert
    fun insert(menu: MemoryEntity?): Long


    @Insert
    fun insertAll(menus: Array<MemoryEntity?>?): LongArray?


    @Query("SELECT * FROM " + MemoryEntity.TABLE_NAME)
    fun selectAll(): Cursor?

    @Query("SELECT * FROM " + MemoryEntity.TABLE_NAME + " WHERE " + MemoryEntity.COLUMN_ID + " = :id")
    fun selectById(id: Long): Cursor?


    @Query("DELETE FROM " + MemoryEntity.TABLE_NAME + " WHERE " + MemoryEntity.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(menu: MemoryEntity?): Int*/
}