package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registro : AppCompatActivity() {
    val baseDatos= BaseDatos(this,"escuela",null,1)
    val db1 = baseDatos.writableDatabase
    val db2= baseDatos.readableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val matricula=findViewById<EditText>(R.id.matricula)
        val nombre=findViewById<EditText>(R.id.nombre)
        val edad=findViewById<EditText>(R.id.edad   )

        findViewById<Button>(R.id.registrar).setOnClickListener {
           val contenedor= ContentValues().apply {
               put("matricula",matricula.text.toString())
               put("nombre",nombre.text.toString())
               put("edad",Integer.getInteger(edad.text.toString()))
           }
            val res=db1.insert("alumno",null,contenedor)
            resultado(res.toInt())
        }

        findViewById<Button>(R.id.actualizar).setOnClickListener {
            val contenedor= ContentValues().apply {
                put("matricula",matricula.text.toString())
                put("nombre",nombre.text.toString())
                put("edad",Integer.getInteger(edad.text.toString()))
            }
            val res=db1.update("alumno",contenedor,"matricula = ?",
                arrayOf(matricula.text.toString()))
            resultado(res)
        }

        findViewById<Button>(R.id.eliminar).setOnClickListener {
           val res= db1.delete("alumno","matricula = ?",arrayOf(matricula.text.toString()))
            resultado(res)
        }

        findViewById<Button>(R.id.consultar).setOnClickListener {
        val cursor=db2.query("alumno",arrayOf("matricula","nombre","edad"),"matricula =  ?",
            arrayOf(matricula.text.toString()),null,null,null)
            if(cursor.moveToFirst()){
    val n=cursor.getString(1)
    val e=cursor.getInt(2)
    nombre.setText(n)
    edad.setText(e)
}else{
    Toast.makeText(this,"Fracaso", Toast.LENGTH_SHORT).show()
}

        }

    }

    fun resultado(res:Int){
        if(res>=1){
            Toast.makeText(this,"éxito", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Fracaso", Toast.LENGTH_SHORT).show()
        }
    }
}