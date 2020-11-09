package com.example.tp3_final.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tp3_final.entities.Nota

@Dao
interface NotaDao {

    @Query("SELECT * from nota_table ORDER BY ID ASC")
    fun getAlphabetizedNotas(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateNota(nota: Nota)

    @Delete
    suspend fun deleteByNota(nota: Nota)


}