package com.example.preexamencorte1kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnIngresar: Button
    private lateinit var btnCerrar: Button
    private lateinit var txtTrabajador: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarComponentes()

        btnIngresar.setOnClickListener {
            ingresar()
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }

    private fun iniciarComponentes() {
        btnIngresar = findViewById(R.id.btnEntrar)
        btnCerrar = findViewById(R.id.btnSalir)
        txtTrabajador = findViewById(R.id.txtTrabajador)
    }

    private fun ingresar() {
        val strTrabajador = "Evaristo Toscano"

        if (txtTrabajador.text.toString() == strTrabajador) {
            val bundle = Bundle()
            bundle.putString("trabajador", strTrabajador)

            val intent = Intent(this@MainActivity, ReciboNominaActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Trabajador no válido", Toast.LENGTH_LONG).show()
        }
    }
}
