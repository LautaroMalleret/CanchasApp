package com.app.canchas.viewholders

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.canchas.R
import com.app.canchas.data.entities.CanchaEntity
import com.app.canchas.repository.Cancha

class CanchaViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val nombre: TextView = itemView.findViewById(R.id.title)
    private val direccion: TextView = itemView.findViewById(R.id.adress)
//    private val ciudad: TextView = itemView.findViewById(R.id.city)
    private val numeroCanchas: TextView = itemView.findViewById(R.id.NumeroCanchas)

    fun render(canchaModel: Cancha){
//        Log.i("holaa", canchaModel.toString())
        nombre.text = canchaModel.nombre
        direccion.text = canchaModel.direccion + " - " + canchaModel.ciudad
//        ciudad.text = canchaModel.ciudad
        numeroCanchas.text = canchaModel.numeroCanchas
    }
}