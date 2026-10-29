package com.example.frioaustral

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "FrioAustral.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE usuarios (user TEXT PRIMARY KEY, pass TEXT)")

        // Creamos un usuario de prueba rápido: admin / 1234
        val values = ContentValues()
        values.put("user", "admin")
        values.put("pass", "1234")
        db.insert("usuarios", null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun login(user: String, pass: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE user=? AND pass=?", arrayOf(user, pass))
        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }
}