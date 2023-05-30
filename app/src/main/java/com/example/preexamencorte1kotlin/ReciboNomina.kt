package com.example.preexamencorte1kotlin

class ReciboNomina {
    // Atributos
    var numRecibo: Int = 0
    var nombre: String = ""
    var horasTrabNormal: Float = 0.0f
    var horasTrabExtras: Float = 0.0f
    var puesto: Int = 0
    var impuestoPorc: Float = 0.0f

    // Constructor
    init {
        numRecibo = 0
        nombre = ""
        horasTrabNormal = 0.0f
        horasTrabExtras = 0.0f
        puesto = 0
        impuestoPorc = 0.0f
    }

    // Métodos
    fun calcularSubtotal(): Float {
        var subtotal = 0.0f
        var pagoBase = 200f

        when (puesto) {
            1 -> {
                pagoBase *= 1.20f
                subtotal = (pagoBase * (horasTrabNormal - horasTrabExtras)) + (horasTrabExtras * (pagoBase * 2))
            }
            2 -> {
                pagoBase *= 1.50f
                subtotal = (pagoBase * horasTrabNormal) + (horasTrabExtras * (pagoBase * 2))
            }
            3 -> {
                pagoBase *= 2f
                subtotal = (pagoBase * horasTrabNormal) + (horasTrabExtras * (pagoBase * 2))
            }
        }

        return subtotal
    }

    fun calcularImpuesto(): Float {
        return calcularSubtotal() * 0.16f
    }

    fun calcularTotal(): Float {
        return calcularSubtotal() - calcularImpuesto()
    }
}
