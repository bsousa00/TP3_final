package com.example.tp3_final.db

import androidx.lifecycle.LiveData
import com.example.tp3_final.dao.NotaDao
import com.example.tp3_final.entities.Nota

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotaRepository(private val notaDao: NotaDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotas: LiveData<List<Nota>> = notaDao.getAlphabetizedNotas()

    suspend fun insert(nota: Nota) {
        notaDao.insert(nota)
    }


    suspend fun deleteAll(){
        notaDao.deleteAll()
    }

    suspend fun updateNota(nota: Nota) {
        notaDao.updateNota(nota)
    }

}