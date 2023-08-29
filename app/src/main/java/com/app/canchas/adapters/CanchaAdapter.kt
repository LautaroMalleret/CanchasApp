package com.app.canchas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.canchas.R
import com.app.canchas.repository.Cancha
import com.app.canchas.viewholders.CanchaViewHolder

class CanchaAdapter(private val listaCanchas: List<Cancha>) : RecyclerView.Adapter<CanchaViewHolder>(){


    //le da al viewholder los item para pintarlos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanchaViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return CanchaViewHolder(layoutInflater.inflate(R.layout.canchas_item,parent, false))
    }

    override fun getItemCount(): Int = listaCanchas.size

    //Recorre los items y llama al render
    override fun onBindViewHolder(holder: CanchaViewHolder, position: Int) {
        val cancha = listaCanchas[position]
        holder.render(cancha)
        }
}