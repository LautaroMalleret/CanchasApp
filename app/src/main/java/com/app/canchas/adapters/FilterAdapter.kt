//package com.app.canchas.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.app.canchas.R
//
//class FilterAdapter(private val options: List<String>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
//    private lateinit var inflater: LayoutInflater
//
//    inner class ViewHolder(val binding:  : RecyclerView.ViewHolder(binding.root) {
//        // Resto del código
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        inflater = LayoutInflater.from(parent.context)
//        val binding = ItemFilterBinding.inflate(inflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val option = options[position]
//        holder.binding.optionTextView.text = option
//        // Resto del código
//    }
//
//    override fun getItemCount(): Int {
//        return options.size
//    }
//}
