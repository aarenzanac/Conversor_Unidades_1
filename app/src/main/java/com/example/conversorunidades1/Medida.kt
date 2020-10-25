package com.example.conversorunidades1

//CLASE PARA LA CREACIÓN DE LOS OBJETOS DE TIPO MEDIDA PARA POSTERIORMENTE CREAR EL GRIDVIEW CON ELLOS

class Medida(nombre: String, imagen: Int){

    var nombre: String = ""
    var imagen: Int = 0

    init{
        this.nombre = nombre
        this.imagen = imagen
    }
}