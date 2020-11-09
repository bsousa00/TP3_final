package com.example.tp3_final.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3_final.R
import com.example.tp3_final.entities.Nota

class LineAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<LineAdapter.NotaViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>() // Cached copy of notas
    val activity =context as ItemClicked

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notaItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerline, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notas[position]
        holder.notaItemView.text = current.id.toString() + " - " + current.nota + "-" + current.texto

        holder.itemView.setOnClickListener(View.OnClickListener { activity.onClickListener(notas.get(position)) })
    }

    internal fun setNotas(notas: List<Nota>) {
        this.notas = notas
        notifyDataSetChanged()
    }

    interface ItemClicked{
        fun onClickListener(nota: Nota)
    }

    override fun getItemCount() = notas.size
}