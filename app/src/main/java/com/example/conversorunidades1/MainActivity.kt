package com.example.conversorunidades1

import android.content.Intent
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

        medidas.add(Medida(getString(R.string.capacidad), R.drawable.bytes))
        medidas.add(Medida(getString(R.string.medida), R.drawable.metros))
        medidas.add(Medida(getString(R.string.volumen), R.drawable.litros))
        medidas.add(Medida(getString(R.string.masa), R.drawable.kilos))
        medidas.add(Medida(getString(R.string.temperatura), R.drawable.grados))

        var grid: GridView = findViewById(R.id.grid)
        val adaptador = Adapter(this, medidas)
        grid.adapter = adaptador


        grid.onItemClickListener = AdapterView.OnItemClickListener { parent, view, posicion, id ->
            Toast.makeText(this, "Ha seleccionado: " + medidas.get(posicion).nombre, Toast.LENGTH_SHORT).show()
            var intent: Intent = Intent(this, PantallaConversioin::class.java)
            intent.putExtra("posicion", posicion)
            startActivity(intent)


        }
    }
}