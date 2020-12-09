package com.example.tp3_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3_final.adapter.LineAdapter
import com.example.tp3_final.api.EndPoints
import com.example.tp3_final.api.OutputPost
import com.example.tp3_final.api.ServiceBuilder
import com.example.tp3_final.api.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

    }


    fun logar(view: View) {

        val utilizador = utilizador.text.toString()
        val password = password.text.toString()

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postLogin(utilizador, password)

        call.enqueue(object : Callback<OutputPost>{
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                if (response.isSuccessful){
                    if (response.body()?.error == false) {
                        val c: OutputPost = response.body()!!
                        Toast.makeText(this@Login, R.string.login_falhou, Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(this@Login, MapsActivity::class.java)
                        Toast.makeText(this@Login, R.string.login_efetuado, Toast.LENGTH_SHORT).show()
                        val d: OutputPost = response.body()!!
                        var token=getSharedPreferences("id", Context.MODE_PRIVATE)
                        var edit= token.edit()
                        edit.putInt("iduser", d.id.toInt())
                        edit.commit()


                        var token2=getSharedPreferences("logout", Context.MODE_PRIVATE)
                        var edit2=token2.edit()
                        edit2.putString("logout_name", utilizador)
                        edit2.commit()

                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun notas(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        var token2 = getSharedPreferences("logout", Context.MODE_PRIVATE)
        if(token2.getString("logout_name", " ")!= " "){
            val intent = Intent(this@Login, MapsActivity::class.java)
            startActivity(intent)
        }
    }



}