package com.app.canchas

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
//import com.app.canchas.data.config.CanchasDataManager
import com.app.canchas.data.config.CanchasDb
import com.app.canchas.data.dao.CanchasDao
import com.app.canchas.data.entities.CanchaEntity
import com.app.canchas.repository.CanchaRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(application: Application) : CanchasDb{
        return Room.databaseBuilder(
            application,
            CanchasDb::class.java,
            "CanchasDataBase"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideDao(canchasDb: CanchasDb) : CanchasDao{
//        return canchasDb.canchasDao()
//    }

    @Provides
    @Singleton
    fun provideRepository(canchasDb: CanchasDb, firestore: FirebaseFirestore): CanchaRepository{
        return CanchaRepository(canchasDb.canchasDao(), firestore)
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

//    @Provides
//    fun provideApplicationContext(application: Application): Context {
//        return application.applicationContext
//    }

//
//    @Provides
//    @Singleton
//    fun provideCanchasDataManager(@ApplicationContext context: Context): Context {
//        return CanchasDataManager(context)
//    }




    //    @Provides
//    @Singleton
//    fun loadCanchasFromJson(context: Context): List<CanchaEntity> {
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

//    @Provides
//    @Singleton
//    fun provideApplicationContext(application: Application): Context {
//        return application.applicationContext
//    }
}