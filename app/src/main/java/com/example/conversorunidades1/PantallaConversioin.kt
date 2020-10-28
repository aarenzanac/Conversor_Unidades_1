package com.example.conversorunidades1

import android.content.Intent
import android.os.Bundle
import android.text.InputType
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

        //RECUPERO EL PARAMETRO ENVIADO DESDE LA ACTIVIDAD PRINCIPAL. EN ESTE CASO LA OPCIÓN DE CONVERSIÓN.

        val intent: Intent = intent
        var posicion: Int = intent.getIntExtra("posicion", 0)

        //CREO SELECTOR DE OPCIONES (SWITCH-CASE JAVA). EN FUNCIÓN DE LA POSICIÓN RECIBIDA DE LA ACTIVIDAD PRINCIPAL
        // CAMBIO EL TITULO DE LA PANTALLA Y LAS UNIDADES DE LOS SPINNERS

        when (posicion) {
            0 -> {
                setTitle(R.string.tituloBytes)
                //CAMBIO EL TECLADO PARA QUE ME PERMITA INTRODUCIR VALORES NEGATIVOS
                textInputCantidad.inputType = InputType.TYPE_CLASS_NUMBER
                imageViewConversion.setImageResource(R.drawable.bytes)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
                    this,
                    R.array.unidadesBytes,
                    android.R.layout.simple_spinner_item
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter

            }
            1 -> {
                setTitle(R.string.tituloMetros)
                //CAMBIO EL TECLADO PARA QUE ME PERMITA INTRODUCIR VALORES NEGATIVOS
                textInputCantidad.inputType = InputType.TYPE_CLASS_NUMBER
                imageViewConversion.setImageResource(R.drawable.metros)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
                    this,
                    R.array.unidadesMetros,
                    android.R.layout.simple_spinner_item
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }
            2 -> {
                setTitle(R.string.tituloLitros)
                //CAMBIO EL TECLADO PARA QUE ME PERMITA INTRODUCIR VALORES NEGATIVOS
                textInputCantidad.inputType = InputType.TYPE_CLASS_NUMBER
                imageViewConversion.setImageResource(R.drawable.litros)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
                    this,
                    R.array.unidadesLitros,
                    android.R.layout.simple_spinner_item
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }
            3 -> {
                setTitle(R.string.tituloKilos)
                //CAMBIO EL TECLADO PARA QUE ME PERMITA INTRODUCIR VALORES NEGATIVOS
                textInputCantidad.inputType = InputType.TYPE_CLASS_NUMBER
                imageViewConversion.setImageResource(R.drawable.kilos)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
                    this,
                    R.array.unidadesGramos,
                    android.R.layout.simple_spinner_item
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter

            }
            4 -> {
                setTitle(R.string.tituloGrados)
                imageViewConversion.setImageResource(R.drawable.grados)
                val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
                    this,
                    R.array.unidadesGrados,
                    android.R.layout.simple_spinner_item
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnidadesOrigen.adapter = spinnerAdapter
                spinnerUnidadesDestino.adapter = spinnerAdapter
            }

        }

        //AL PULSAR EL BOTON CONVERTIR, VA COMPROBANDO SI ESTAN TODOS LOS CAMPOS
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
                        //SI ESTÁN TODOS LOS CAMPOS, SELECTOR (SWITCH-CASE JAVA) PARA ELEGIR EL METODO DE CONVERSIÓN
                        // EN FUNCIÓN DE LA OPCIÓN ECIVIDA DE LA ACTIVIDAD PRINCIPAL

                        textViewResultado.text = ""
                        when(posicion){
                            0 -> {
                                calcularBytes(cantidad, origen, destino)
                            }
                            1 -> {
                                calcularGramosLitrosMetros(cantidad, origen, destino)
                            }
                            2 -> {
                                calcularGramosLitrosMetros(cantidad, origen, destino)
                            }
                            3 -> {
                                calcularGramosLitrosMetros(cantidad, origen, destino)
                            }
                            4 -> {
                                calcularTemperatura(cantidad, origen, destino)
                            }
                        }
                    }
                }
            }
        }

        //BOTON PARA VOLVER A LA ACTIVIDAD PRINCIPAL

        buttonVolver.setOnClickListener{
            finish()
        }

    }

    //MÉTODO PARA OBTENER LA CANTIDAD A CONVERTIR. COMPRUEBA SI HAY DATO INTRODUCIDO

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

    //MÉTODO PARA OBTENER LA UNIDAD DE ORIGEN DE LA CONVERSIÓN Y SU POSICIÓN EN EL ARRAY DE UNIDADES

    private fun obtenerUnidadOrigen(){
        origen = spinnerUnidadesOrigen.selectedItemPosition
        unidadOrigen = spinnerUnidadesOrigen.getItemAtPosition(origen).toString()
    }

    //MÉTODO PARA OBTENER LA UNIDAD DE DESTINO DE LA CONVERSIÓN Y SU POSICIÓN EN EL ARRAY DE UNIDADES

    private fun obtenerUnidadDestino(){
        destino = spinnerUnidadesDestino.selectedItemPosition
        unidadDestino = spinnerUnidadesDestino.getItemAtPosition(destino).toString()
    }

    /*TODOS LOS MÉTODOS PARA CALCULAR LAS CONVERSIONES.
    LA FORMA DE CALCULAR ES LA SIGUIENTE:
    1- EN PRIMER LUGAR, EN FUNCIÓN DE LAS POSICIONES EN EL ARRAY (VARIABLES ORIGEN Y DESTINO),
        SABEMOS SI LO QUE QUIERO ES CONVERTIR EN SENTIDO ASCENDENTE (KILOMETROS A METROS) O DESCENDENTE (METROS A KILOMETROS).
        SI LAS UNIDADES SON LAS MISMAS, MUESTRA EL RESULTADO.
    2- EN FUNCIÓN DEL SENTIDO DE CONVERSIÓN, SE UTILIZA LA FÓRMULA CORRESPONDIENTE PARA CADA CASO.
        EN CASO DE TEMPERATURA, SU PROPIA FÓRMULA.
        EN CASO DE LITROS, KILOS Y METROS, OTRA FORMULA LINEAL. MULTIPLICAR O DIVIDIR ENTRE 10

    */

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

    private fun calcularGramosLitrosMetros(cantidad: Double, origen: Int, destino: Int){

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


    /*PARA EL CASO DE BYTES, HE CONSIDERADO LO SIGUIENTE.
    COMO LA CONVERSIÓN NO ES LINEAL PORQUE EL PRIMER PASO DE BIT A BYTE ES X8, HE CONSIDERADO TRES SUPUESTOS PARA CADA SENTIDO DE CONVERSIÓN
    1- SI QUEREMOS CONVERTIR BIT A BYTE O VICEVERSA, SIMPLEMENTE MULTIPLICO O DIVIDO POR 8
    2- SI QUEREMOS CONVERTIR DE BYTE A GEOPBYTE (SIN LLEGAR A BITS), MULTIPLICO O DIVIDO POR 1024
    3- SU QUEREMOS CONVERTIR DE BIT A GEOPBYTE (HASTA O DESDE BITS), HAY QUE AÑADIR UNA MULTIPLICACIÓN O DIVISIÓN POR 8.
    */

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