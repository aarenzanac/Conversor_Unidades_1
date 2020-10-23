package com.example.conversorunidades1

import android.os.Bundle
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

        buttonVolver.setOnClickListener{
            finish()
        }

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
                resultado = (cantidad * 8) *(1024.00.pow((origen-1)-destino))
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