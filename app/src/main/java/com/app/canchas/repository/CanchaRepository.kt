package com.app.canchas.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.app.canchas.data.dao.CanchasDao
import com.app.canchas.data.entities.CanchaEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.json.JSONArray
//import com.app.canchas.data.entities.CanchasDataManager
import javax.inject.Inject

class CanchaRepository @Inject constructor(
    private val canchaDao: CanchasDao,
    private val firestore: FirebaseFirestore
) {

    suspend fun getCanchas(): List<Cancha> {
        val listCanchasDao = canchaDao.getAll()
        return mapearEntityToCancha(listCanchasDao)
    }



    suspend fun getAllCanchasFirestore(){
        val listEntities = mutableListOf<CanchaEntity>()
        try {
            val collection = firestore.collection("Canchas").get().await()
            for (document in collection.documents) {
                val cancha = CanchaEntity(
                    null,
                    document.data?.get("Nombre").toString(),
                    document.data?.get("Direccion").toString(),
                    document.data?.get("Barrio").toString(),
                    document.data?.get("Ciudad").toString(),
                    document.data?.get("Telefono").toString(),
                    document.data?.get("Mail").toString(),
                    document.data?.get("CantidadCanchas").toString(),
                    document.data?.get("NumeroCanchas").toString(),
                    document.data?.get("Tipo").toString()
                )
                listEntities.add(cancha)
            }

            withContext(Dispatchers.IO) {

                val baseDeDatosCreada = canchaDao.getNumberOfRows() > 0
                if (!baseDeDatosCreada) {
                    for (entity in listEntities) {
                        canchaDao.insert(entity)
                    }
                }

            }
        } catch (e: Exception) {
            // Manejo de errores si es necesario
        }

    }

    suspend fun obtenerCanchasFiltradas(filtroCiudad:String, filtroNumeroCancha: String) : List<Cancha>{
        lateinit var listaCanchasDao : List<CanchaEntity>
        if(filtroCiudad.isEmpty() && filtroNumeroCancha.isEmpty()){
            listaCanchasDao = canchaDao.getAll()
        }
        else if(filtroCiudad.isEmpty() && filtroNumeroCancha.isNotEmpty()){
            listaCanchasDao = canchaDao.getCanchasFiltradasPorNumero(filtroNumeroCancha)
        }
        else if(filtroNumeroCancha.isEmpty() && filtroCiudad.isNotEmpty()){
            listaCanchasDao = canchaDao.getCanchasFiltradasPorCiudad(filtroCiudad)
        }
        else {
            listaCanchasDao = canchaDao.getCanchasFiltradasCiudadYNumero(filtroCiudad,filtroNumeroCancha)
        }
            return mapearEntityToCancha(listaCanchasDao)
    }

    private fun mapearEntityToCancha(listEntity: List<CanchaEntity>):List<Cancha>{
        return listEntity.map { canchaDao ->

            Cancha(
                nombre = canchaDao.nombre,
                direccion = canchaDao.direccion,
                barrio = canchaDao.barrio,
                ciudad = canchaDao.ciudad,
                telefono = canchaDao.telefono,
                mail = canchaDao.mail,
                cantidadCanchas = canchaDao.cantidadCanchas,
                numeroCanchas = canchaDao.numeroCanchas,
                tipo = canchaDao.tipo
            )
        }
    }
}

//suspend fun loadCanchasFromJson(context: Context) {
//    withContext(Dispatchers.IO) {
//
//        val baseDeDatosCreada = canchaDao.getNumberOfRows() > 0
//        if (!baseDeDatosCreada) {
//            val listaEntities = leerJson(context)
//            for (entity in listaEntities) {
//                canchaDao.insert(entity)
//            }
//        }
//    }
//
////        withContext(Dispatchers.IO) {
////        val json = context.assets.open("CanchasDB.json").bufferedReader().use { it.readText() }
////        Log.d("holaa", json)
////        val gson = Gson()
////        val canchasListType = object : TypeToken<List<CanchaEntity>>() {}.type
////        val canchasEntities = gson.fromJson<List<CanchaEntity>>(json, canchasListType)
////        Log.d("hola", canchasEntities.toString())
////        canchaDao.insertAll(canchasEntities)
////        }
//}

//    private fun leerJson(context : Context): List<CanchaEntity> {
//
//        val jsonString = context.assets.open("CanchasDB.json").bufferedReader().use { it.readText() }
//        val jsonArray = JSONArray(jsonString)
//        val canchasList = mutableListOf<CanchaEntity>()
//
//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            val cancha = CanchaEntity(
//                null,
//                jsonObject.getString("Nombre"),
//                jsonObject.getString("Direccion"),
//                jsonObject.getString("Barrio"),
//                jsonObject.getString("Ciudad"),
//                jsonObject.getString("Telefono"),
//                jsonObject.getString("Mail"),
//                jsonObject.getString("CantidadCanchas"),
//                jsonObject.getString("NumeroCanchas"),
//                jsonObject.getString("Tipo")
//            )
//            canchasList.add(cancha)
//        }
//
//        return canchasList
//    }