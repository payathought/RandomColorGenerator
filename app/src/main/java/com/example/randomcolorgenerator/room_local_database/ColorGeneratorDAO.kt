package com.example.randomcolorgenerator.room_local_database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ColorGeneratorDAO {
    @Query("SELECT * FROM tblSchemeNameList")
    fun getAllFromSchemeNameList(): List<ColorGeneratorEntities>


    @Query("SELECT * FROM tblSchemeNameList WHERE  schemeName_id = :id")
    fun findByPrimaryKey(id : Int) : ColorGeneratorEntities


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlltoColorGen(vararg todo : ColorGeneratorEntities)

    @Delete
    fun delete(todo : ColorGeneratorEntities)
    @Update
    fun updateTodo(vararg todos : ColorGeneratorEntities)


    @Query("SELECT * FROM tblSchemeNameList")
    fun getAllFromSchemeNameListLiveData(): LiveData<List<ColorGeneratorEntities>>

}

