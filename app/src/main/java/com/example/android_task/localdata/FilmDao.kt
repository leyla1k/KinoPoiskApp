package com.example.android_task.localdata

import androidx.room.Dao


@Dao
interface FilmDao {


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