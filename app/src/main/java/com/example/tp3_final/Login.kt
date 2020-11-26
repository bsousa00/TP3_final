package com.example.tp3_final

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun logar(view: View) {
        val intent = Intent(this, com.example.tp3_final.MapsActivity::class.java)
        startActivity(intent)

    }

    fun notas(view: View) {
        val intent = Intent(this, com.example.tp3_final.MainActivity::class.java)
        startActivity(intent)
    }
}