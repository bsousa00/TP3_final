package com.example.tp3_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.tp3_final.adapter.LineAdapter
import com.example.tp3_final.entities.Nota
import com.example.tp3_final.viewModel.NotaViewModel

class MainActivity : AppCompatActivity(), LineAdapter.ItemClicked {

    private lateinit var notaViewModel: NotaViewModel
    private val newWordActivityRequestCode = 1
    private val updateNotaActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = LineAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, Observer { notas ->
            // Update the cached copy of the words in the adapter.
            notas?.let { adapter.setNotas(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNota::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val pdelete = data?.getStringExtra(com.example.tp3_final.Update.EXTRA_REPLY_DELETE)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val pnota = data?.getStringExtra(AddNota.EXTRA_REPLY_NOTA)
            val ptexto = data?.getStringExtra(AddNota.EXTRA_REPLY_TEXTO)

            if (pnota != null && ptexto != null) {
                val nota = Nota(nota = pnota, texto = ptexto)
                notaViewModel.insert(nota)
            }

        } else if (requestCode == updateNotaActivityRequestCode && resultCode == Activity.RESULT_OK && pdelete == "update") {
            val pid = data?.getIntExtra(com.example.tp3_final.Update.EXTRA_REPLY_ID, -1)
            val pnota = data?.getStringExtra(com.example.tp3_final.Update.EXTRA_REPLY_NOTA)
            val ptexto = data?.getStringExtra(com.example.tp3_final.Update.EXTRA_REPLY_TEXTO)

            if (pid != null && pnota != null && ptexto != null) {
                val nota = Nota(id = pid, nota = pnota, texto = ptexto)
                notaViewModel.updateNota(nota)
            }
        } else if (pdelete != "update") {
            val pid = data?.getIntExtra(com.example.tp3_final.Update.EXTRA_REPLY_ID, -1)
            val pnota = data?.getStringExtra(com.example.tp3_final.Update.EXTRA_REPLY_NOTA)
            val ptexto = data?.getStringExtra(com.example.tp3_final.Update.EXTRA_REPLY_TEXTO)

            if (pid != -1 && pnota != null && ptexto != null) {
                val nota = Nota(id = pid, nota = pnota, texto = ptexto)
                notaViewModel.deleteByNota(nota)
            } else {
                Toast.makeText(
                        applicationContext,
                        "Nota vazia: não inseriu nenhuma nota",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClickListener(nota: Nota) {
        val intent = Intent(this, com.example.tp3_final.Update::class.java)


        intent.putExtra("id", nota.id)
        intent.putExtra("nota", nota.nota)
        intent.putExtra("texto", nota.texto)

        startActivityForResult(intent, updateNotaActivityRequestCode)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.apagartudo -> {
                notaViewModel.deleteAll()
                true
            }

            R.id.alterar -> {
                val nota = Nota(id = 1, nota = "xxx", texto = "xxx")
                notaViewModel.updateNota(nota)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}





