package com.example.tp3_final.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tp3_final.db.NotaRepository
import com.example.tp3_final.db.NotaDB
import com.example.tp3_final.entities.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotaRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotas: LiveData<List<Nota>>

    init {
        val notasDao = NotaDB.getDatabase(application, viewModelScope).notaDao()
        repository = NotaRepository(notasDao)
        allNotas = repository.allNotas
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(nota)
    }

    // delete all
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun updateNota(nota: Nota) = viewModelScope.launch {
        repository.updateNota(nota)
    }

    fun deleteByNota(nota: Nota) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByNota(nota)
    }
}