package com.example.preexamencorte1kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ReciboNominaActivity : AppCompatActivity() {
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    private lateinit var txtNumRecibo: EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtHorasTrabajadas: EditText
    private lateinit var txtHorasExtras: EditText
    private lateinit var txtSubTotal: EditText
    private lateinit var txtImpuestos: EditText
    private lateinit var txtTotalPagar: EditText

    private lateinit var checkBoxAuxiliar: CheckBox
    private lateinit var checkBoxAlbanil: CheckBox
    private lateinit var checkBoxIng: CheckBox

    private lateinit var nomina: ReciboNomina

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recibo)
        iniciarComponentes()

        if (checkBoxAuxiliar.isChecked) {
            checkBoxAlbanil.isEnabled = false
            checkBoxIng.isEnabled = false
        }

        if (checkBoxAlbanil.isChecked) {
            checkBoxAuxiliar.isEnabled = false
            checkBoxIng.isEnabled = false
        }

        if (checkBoxIng.isChecked) {
            checkBoxAuxiliar.isEnabled = false
            checkBoxAlbanil.isEnabled = false
        }

        btnCalcular.setOnClickListener {
            btnCalcular()
        }

        btnLimpiar.setOnClickListener {
            limpiar()
        }

        btnRegresar.setOnClickListener {
            regresar()
        }

        checkBoxAuxiliar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxAlbanil.isChecked = false
                checkBoxIng.isChecked = false
            }
        }

        checkBoxAlbanil.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxAuxiliar.isChecked = false
                checkBoxIng.isChecked = false
            }
        }

        checkBoxIng.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxAuxiliar.isChecked = false
                checkBoxAlbanil.isChecked = false
            }
        }
    }

    private fun iniciarComponentes() {
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
        txtNumRecibo = findViewById(R.id.txtNumRecibo)
        txtNombre = findViewById(R.id.txtNombre)
        txtHorasTrabajadas = findViewById(R.id.txtHorasTrabajadas)
        txtHorasExtras = findViewById(R.id.txtHorasExtras)
        txtSubTotal = findViewById(R.id.txtSubtotal)
        txtImpuestos = findViewById(R.id.txtImpuestos)
        txtTotalPagar = findViewById(R.id.txtTotalPagar)
        checkBoxAuxiliar = findViewById(R.id.checkBoxAuxiliar)
        checkBoxAlbanil = findViewById(R.id.checkBoxAlbanil)
        checkBoxIng = findViewById(R.id.checkBoxIng)

        nomina = ReciboNomina()
    }

    private fun limpiar() {
        txtNumRecibo.text.clear()
        txtNombre.text.clear()
        txtHorasTrabajadas.text.clear()
        txtHorasExtras.text.clear()
        txtSubTotal.text.clear()
        txtImpuestos.text.clear()
        txtTotalPagar.text.clear()
        checkBoxAuxiliar.isChecked = false
        checkBoxAlbanil.isChecked = false
        checkBoxIng.isChecked = false
    }

    private fun regresar() {
        val confirmar = AlertDialog.Builder(this)
        confirmar.setTitle("Nomina")
        confirmar.setMessage("¿Deseas regresar?")
        confirmar.setPositiveButton("Confirmar") { _, _ ->
            finish()
        }
        confirmar.setNegativeButton("Cancelar") { _, _ -> }
        confirmar.show()
    }

    private fun btnCalcular() {
        if (validar() != -1) {
            nomina.horasTrabNormal = txtHorasTrabajadas.text.toString().toFloat()
            nomina.horasTrabExtras = txtHorasExtras.text.toString().toFloat()

            validarCheckBox()
            txtSubTotal.setText(nomina.calcularSubtotal().toString())
            txtImpuestos.setText(nomina.calcularImpuesto().toString())
            txtTotalPagar.setText(nomina.calcularTotal().toString())
        }
    }

    private fun validarCheckBox() {
        if (checkBoxAuxiliar.isChecked) {
            nomina.puesto = 1
        }
        if (checkBoxAlbanil.isChecked) {
            nomina.puesto = 2
        }
        if (checkBoxIng.isChecked) {
            nomina.puesto = 3
        }
    }

    private fun validar(): Int {
        if (txtNumRecibo.text.toString().isEmpty() || txtNombre.text.toString().isEmpty()
            || txtHorasTrabajadas.text.toString().isEmpty() || txtHorasExtras.text.toString().isEmpty()
        ) {
            Toast.makeText(applicationContext, "Faltan datos", Toast.LENGTH_LONG).show()
            return -1
        }
        return 0
    }
}
