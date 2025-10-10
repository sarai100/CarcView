package com.example.myapplication

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/* 
Course 
name: My Application
by Master Ivàn Azamar Palma
System Computing Engineering 
copylef
08/10/2025  at 18:13
*/

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE alumno(matricula TEXT PRIMARY KEY, nombre TEXT, edad INTEGER)")
        db?.execSQL("CREATE TABLE curso(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, creditos INTEGER)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS alumno")
        db?.execSQL("DROP TABLE IF EXISTS curso")
        onCreate(db)
    }
}