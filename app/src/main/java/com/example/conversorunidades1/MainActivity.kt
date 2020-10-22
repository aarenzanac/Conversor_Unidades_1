package com.example.conversorunidades1

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var medidas = ArrayList<Medida>()

        medidas.add(Medida("Capacidad (Bytes)", R.drawable.bytes))
        medidas.add(Medida("Medida (Metros)", R.drawable.metros))
        medidas.add(Medida("Volumen (Litros)", R.drawable.litros))
        medidas.add(Medida("Masa (Kilos)", R.drawable.kilos))
        medidas.add(Medida("Temperatura (Grados)", R.drawable.grados))

        var grid: GridView = findViewById(R.id.grid)
        val adaptador = Adapter(this, medidas)
        grid.adapter = adaptador

        grid.onItemClickListener = AdapterView.OnItemClickListener { parent, view, posicion, id ->

            Toast.makeText(this, medidas.get(posicion).nombre, Toast.LENGTH_SHORT).show()
        }
    }
}