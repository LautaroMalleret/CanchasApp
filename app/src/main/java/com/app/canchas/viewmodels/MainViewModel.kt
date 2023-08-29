package com.app.canchas.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.app.canchas.data.config.CanchasDataManager
import com.app.canchas.repository.Cancha
import com.app.canchas.repository.CanchaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val canchaRepository: CanchaRepository
): ViewModel() {

    //lista para tener las canchas que muestro en la ui, la actualizo por cada consulta
    var listaCanchas = mutableListOf<Cancha>()

    suspend fun obtenerCanchas() {
        val lista = mutableListOf<Cancha>()                 //creo lista con valores solicitados
        lista.addAll(canchaRepository.getCanchas())
        listaCanchas = lista
    //        listaCanchas.addAll(canchaRepository.getCanchas())
//        return canchaRepository.getCanchas()
    }


    suspend fun obtenerCanchasFiltradas(filtroCiudad: String, filtroNumeroCancha: String){
        val ciudad = if (filtroCiudad == "TODAS LAS CIUDADES") "" else filtroCiudad
        val numeroCancha = if (filtroNumeroCancha == "Nº CANCHA") "" else filtroNumeroCancha


        val listaFiltrada = mutableListOf<Cancha>()             //creo lista con valores solicitados
        listaFiltrada.addAll(canchaRepository.obtenerCanchasFiltradas(ciudad, numeroCancha))
//        Log.i("hola", listaFiltrada.toString())
        listaCanchas = listaFiltrada
    }

    suspend fun cargarTodasLasCanchas() {
        canchaRepository.getAllCanchasFirestore()
    }
}


//    suspend fun cargarCanchas(context: Context) {
//        canchaRepository.loadCanchasFromJson(context)
//    }