package com.app.canchas.data.config

import android.content.Context
import android.content.res.AssetManager
import com.app.canchas.data.entities.CanchaEntity
import com.google.gson.Gson
import javax.inject.Singleton

//@Singleton
//class CanchasDataManager (private val context: Context) {
//
//    fun loadCanchasFromJson(): List<CanchaEntity> {
//        val assetManager: AssetManager = context.assets
//        val fileName = "miarchivo.json" // Reemplazar con el nombre real de tu archivo JSON
//
//        val canchasList = mutableListOf<CanchaEntity>()
//
//        try {
//            val inputStream = assetManager.open(fileName)
//            val bufferedReader = inputStream.bufferedReader()
//            val jsonContent = bufferedReader.use { it.readText() }
//
//            val gson = Gson() // Crear una instancia de la clase Gson
//            val canchasArray = gson.fromJson(jsonContent, Array<CanchaEntity>::class.java)
//
//            canchasList.addAll(canchasArray) // Agregar todos los elementos del array a la lista
//
//            bufferedReader.close() // Cerrar el lector de archivos
//            inputStream.close() // Cerrar el flujo de entrada del archivo
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return canchasList // Devolver la lista de entidades CanchaEntity
//    }
//}