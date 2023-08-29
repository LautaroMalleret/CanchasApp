package com.app.canchas.data.config

import android.app.Application
//import com.app.canchas.data.config.CanchasApp.Companion.mainViewModel
import dagger.hilt.android.HiltAndroidApp


//iniciar la db
@HiltAndroidApp
class CanchasApp: Application() {

//    fun loadCanchas(): List<CanchaEntity> {
//        val json = assets.open("tucarchivo.json").bufferedReader().use { it.readText() }
//        val gson = Gson()
//        val canchasListType = object : TypeToken<List<CanchaEntity>>() {}.type
//        return gson.fromJson(json, canchasListType)
//    }
//    companion object{
//        lateinit var db: CanchasDb
//        lateinit var dao: CanchasDao
//        lateinit var repository: CanchaRepository
//        lateinit var mainViewModel: MainViewModel
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//         db = Room.databaseBuilder(
//                this,
//                CanchasDb::class.java,
//                "CanchasDB.db"
//                ).createFromAsset("CanchasDB")
//            .fallbackToDestructiveMigration()
//            .build()
//Log.d("db", db.toString())
//
//
//        dao = db.canchasDao()
//        repository = CanchaRepository(dao)
//        mainViewModel= MainViewModel(repository)

//    }

}
