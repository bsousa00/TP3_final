package com.example.tp3_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class Update : AppCompatActivity() {

    private lateinit var notaText: EditText
    private lateinit var textoText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_nota)


        val id = intent.getIntExtra("id",-2)

        notaText = findViewById(R.id.atualizar_nota)
        textoText = findViewById(R.id.atualizar_texto)

        var nota = intent.getStringExtra("nota")
        var texto = intent.getStringExtra("texto")

        notaText.setText(nota)
        textoText.setText(texto)


        val button_update = findViewById<Button>(R.id.button_update)
        button_update.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(notaText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_DELETE,"update")
                replyIntent.putExtra(EXTRA_REPLY_ID,id)
                replyIntent.putExtra(EXTRA_REPLY_NOTA, notaText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_TEXTO, textoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        val button_delete = findViewById<Button>(R.id.button_delete)
        button_delete.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(notaText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_DELETE,"delete")
                replyIntent.putExtra(EXTRA_REPLY_ID,id)
                replyIntent.putExtra(EXTRA_REPLY_NOTA, notaText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_TEXTO, textoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_DELETE = "com.example.android.delete"
        const val EXTRA_REPLY_ID = "com.example.android.id"
        const val EXTRA_REPLY_NOTA = "com.example.android.nota"
        const val EXTRA_REPLY_TEXTO = "com.example.android.texto"
    }
}