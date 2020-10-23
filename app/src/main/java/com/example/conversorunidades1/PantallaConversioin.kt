package com.example.conversorunidades1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.pantalla_conversion.*
import kotlin.math.pow


class PantallaConversioin : AppCompatActivity() {

    var cantidad: Double = 0.00
    var origen: Int = -1
    var unidadOrigen: String = ""
    var destino: Int = -1
    var unidadDestino: String = ""
    var resultado: Double = 0.00


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_conversion)
        val intent: Intent = intent
        var posicion: Int = intent.getIntExtra("posicion", 0)


        when (posicion) {
            0 -> {
                setTitle(R.string.tituloBytes)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.unidadesBytes, R.layout.pantalla_conversion)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter

            }
            1 -> {
                setTitle(R.string.tituloMetros)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.unidadesMetros, R.layout.pantalla_conversion)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }
            2 -> {
                setTitle(R.string.tituloLitros)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.unidadesLitros, R.layout.pantalla_conversion)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }
            3 -> {
                setTitle(R.string.tituloKilos)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.unidadesGramos, R.layout.pantalla_conversion)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter

            }
            4 -> {
                setTitle(R.string.tituloGrados)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.unidadesGrados, R.layout.pantalla_conversion)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }

        }

        buttonConvertir.setOnClickListener {
            cantidad = obtenerCantidad()
            if (cantidad != 0.00){
                obtenerUnidadOrigen()
                if(origen < 1) {
                    textViewResultado.setText(R.string.errorOrigen)
                }else{
                    textViewResultado.text = ""
                    obtenerUnidadDestino()
                    if(destino < 1){
                        textViewResultado.setText(R.string.errorDestino)
                    }else{
                        textViewResultado.text = ""
                        //calcular(cantidad, origen, destino)
                    }
                }
            }
        }

        buttonVolver.setOnClickListener{
            finish()
        }

    }

    private fun obtenerCantidad(): Double{
        if(textInputCantidad.text.isNullOrBlank() || textInputCantidad.text.toString().toDouble() == 0.00 ){
            textViewResultado.setText(R.string.errorCantidad)
            return 0.00
        }else{
            val input: Double = textInputCantidad.text.toString().toDouble()
            println("**************La cantidad introducida es: ${input} *********************")
            return input
        }
    }

    private fun obtenerUnidadOrigen(){
        origen = spinnerUnidadesOrigen.selectedItemPosition
        unidadOrigen = spinnerUnidadesOrigen.getItemAtPosition(origen).toString()
    }

    private fun obtenerUnidadDestino(){
        destino = spinnerUnidadesDestino.selectedItemPosition
        unidadDestino = spinnerUnidadesDestino.getItemAtPosition(destino).toString()
    }

    private fun calcularTemperatura(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen < destino ){
            resultado = ((cantidad * 9) /5) + 32.00
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }else{
            resultado = (((cantidad - 32) * 5) /9)
            resultado = String.format("%.3f", resultado).toDouble()
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }
    }

    private fun calcularGramos(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen > destino ){
            resultado = cantidad*(10.00.pow(origen - destino))
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }else{
            var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
            var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
            for(num in origen..destino-1) {
                operacion = cantidadDivision / 10.00
                cantidadDivision = operacion
            }
            resultado = cantidadDivision
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }
    }

    private fun calcularLitros(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen > destino ){
            resultado = cantidad*(10.00.pow(origen - destino))
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }else{
            var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
            var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
            for(num in origen..destino-1) {
                operacion = cantidadDivision / 10.00
                cantidadDivision = operacion
            }
            resultado = cantidadDivision
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }
    }

    private fun calcularMetros(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen > destino ){
            resultado = cantidad*(10.00.pow(origen - destino))
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }else{
            var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
            var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
            for(num in origen..destino-1) {
                operacion = cantidadDivision / 10.00
                cantidadDivision = operacion
            }
            resultado = cantidadDivision
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }
    }

    private fun calcularBytes(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen > destino ){
            if(origen == 2 && destino == 1){
                resultado = cantidad * 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else if(origen > 2 && destino == 1){
                resultado = (cantidad * 8) *(1024.00.pow((origen - 1) - destino))
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else {
                resultado = cantidad*(1024.00.pow(origen - destino))
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }

        }else{
            if(origen == 1 && destino == 2){
                resultado = cantidad / 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else if(origen == 1 && destino > 2){
                var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
                var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
                for(num in origen..destino-2) {
                    operacion = cantidadDivision / 1024.00
                    cantidadDivision = operacion
                }
                resultado = cantidadDivision / 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else{
                var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
                var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
                for(num in origen..destino-1) {
                    operacion = cantidadDivision / 1024.00
                    cantidadDivision = operacion
                }
                resultado = cantidadDivision
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }

        }
    }
}