package com.app.canchas

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.canchas.adapters.CanchaAdapter
//import com.app.canchas.data.config.CanchasApp
//import com.app.canchas.data.config.CanchasApp.Companion.mainViewModel
import com.app.canchas.databinding.ActivityMainBinding
import com.app.canchas.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

//    private lateinit var canchaAdapter: CanchaAdapter
    private lateinit var recyclerView: RecyclerView

    private var SeleccionCiudad: String = ""
    private var SeleccionNumeroCancha: String = ""
    lateinit var filtroCiudad: Spinner
    lateinit var filtroNumeroCancha: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVariable()

        lifecycleScope.launch {

            initRecyclerView()

            filtroCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    SeleccionCiudad = parent?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    SeleccionCiudad = "TODAS LAS CIUDADES"
                }
            }

            filtroNumeroCancha.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    SeleccionNumeroCancha = parent?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    SeleccionNumeroCancha = "Nº CANCHA"
                }
            }

            binding.btnFiltrar.setOnClickListener {
                lifecycleScope.launch {
                    mainViewModel.obtenerCanchasFiltradas(SeleccionCiudad, SeleccionNumeroCancha) //obtengo las canchas de firestore
//                    Log.i("hola mvm filt", mainViewModel.listaCanchas.toString())

                    val canchaAdapterFilter = CanchaAdapter(mainViewModel.listaCanchas)
                    recyclerView.adapter = canchaAdapterFilter
                }
            }
        }
    }

    private suspend fun initRecyclerView() {

        recyclerView = binding.recyclerViewCanchas
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainViewModel.cargarTodasLasCanchas() //obtengo las canchas de firestore
        mainViewModel.obtenerCanchas()

//Log.i("hola mvm", mainViewModel.listaCanchas.toString())
        val canchaAdapter = CanchaAdapter(mainViewModel.listaCanchas)
        recyclerView.adapter = canchaAdapter

    }


    private fun initVariable() {


        filtroCiudad = binding.filtro1
        val ciudades = listOf(
            "CIUDADES", "ADROGUÉ", "ALMIRANTE BROWN", "AVELLANEDA", "BAHÍA BLANCA", "BANFIELD",
            "BECAAR", "BELLAVISTA", "BENAVIDEZ", "BERAZATEGUI", "BERNAL", "BOULOGNE",
            "CABA", "CASEROS", "CIUDAD EVITA", "CIUDADELA", "DON TORCUATO", "EL PALOMAR",
            "ESCOBAR", "ESTEBAN ECHEVERRIA", "EZEIZA", "FLORENCIO VARELA", "GARÍN",
            "GENERAL PACHECO", "HAEDO", "HURLINGHAM", "ITUZAINGO", "JOSÉ C. PAZ",
            "JOSE LEÓN SUAREZ", "LA MATANZA", "LA PLATA", "LANUS", "LOMAS DE ZAMORA",
            "LUIS GUILLÓN", "MAR DEL PLATA", "MARTÍNEZ", "MERLO", "MORENO", "MORON",
            "NUÑEZ", "OLIVOS", "PACHECO", "PERGAMINO", "PIGUE", "PILAR", "QUILMES",
            "RAMOS MEJIA", "RANELAGH", "SAENZ PEÑA", "SAN FERNANDO", "SAN ISIDRO",
            "SAN JUSTO", "SAN MARTIN", "SAN MIGUEL", "SAN VICENTE", "TANDIL", "TEMPERLEY",
            "TIGRE", "TORTUGUITAS", "VICENTE LOPEZ", "VILLA ADELINA", "VILLA BALLESTER",
            "VILLA DE MAYO", "WILDE"
        )
        val adapterFiltroCiudad = ArrayAdapter(this, R.layout.simple_spinner_item, ciudades)
        adapterFiltroCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        filtroCiudad.adapter = adapterFiltroCiudad


        filtroNumeroCancha = binding.filtro2
        val numeros = listOf("Nº CANCHA", "5", "6", "7", "8", "9", "11")
        val adapterFiltroNumeroCancha = ArrayAdapter(this, R.layout.simple_spinner_item, numeros)
        adapterFiltroNumeroCancha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        filtroNumeroCancha.adapter = adapterFiltroNumeroCancha

    }
}