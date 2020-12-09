package com.example.tp3_final

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3_final.adapter.LineAdapter
import com.example.tp3_final.api.EndPoints
import com.example.tp3_final.api.OutputPost
import com.example.tp3_final.api.ServiceBuilder
import com.example.tp3_final.api.User
import kotlinx.android.synthetic.main.activity_add_problema.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProblema : AppCompatActivity() {

    private var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_problema)

        var lat = intent.getStringExtra("Lat")
        var long = intent.getStringExtra("Lng")

        findViewById<TextView>(R.id.latitude_add).setText(lat)
        findViewById<TextView>(R.id.longitude_add).setText(long)

        var token=getSharedPreferences("id", Context.MODE_PRIVATE)
        id = token.getInt("iduser", 0)
        findViewById<TextView>(R.id.ver_id).setText(id.toString())

    }

    fun save_problema(view: View) {

        val intent = Intent(this, MapsActivity::class.java)
        val lat = latitude_add.text.toString()
        val lng = longitude_add.text.toString()
        val descr = descricao.text.toString()


        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getProblemasadicionar(lat, lng, descr, id)

        call.enqueue(object : Callback<OutputPost>{
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                if (response.isSuccessful){
                    Toast.makeText(this@AddProblema, R.string.problema_inserido, Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                Toast.makeText(this@AddProblema, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


}