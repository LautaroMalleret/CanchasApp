package com.app.canchas.data.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.canchas.data.entities.CanchaEntity

@Dao
interface CanchasDao {

    @Query("SELECT * FROM CanchasDB ORDER BY Nombre ASC")
    suspend fun getAll(): List<CanchaEntity>

    @Query("SELECT * FROM CanchasDB WHERE Ciudad LIKE :ciudad ORDER BY Nombre ASC")
    suspend fun getCanchasFiltradasPorCiudad(ciudad:String): List<CanchaEntity>


    @Query("SELECT * FROM CanchasDB WHERE NumeroCanchas LIKE '%' || :numero || '%' ORDER BY Nombre ASC")
    suspend fun getCanchasFiltradasPorNumero(numero: String):List<CanchaEntity>

    @Query("SELECT  * FROM CanchasDB Where Ciudad LIKE :ciudad AND NumeroCanchas LIKE '%' || :numero || '%' ORDER BY Nombre ASC")
    suspend fun getCanchasFiltradasCiudadYNumero(ciudad: String,numero: String): List<CanchaEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)//por si paso un valor repetido.
    suspend fun insert(canchaEntity: CanchaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(canchasEntities: List<CanchaEntity>?)

    @Query("SELECT COUNT(*) FROM CanchasDB")
    fun getNumberOfRows(): Int

//    @Query("SELECT * FROM CanchasDB WHERE Nombre LIKE '%' || :nombre || '%'")
//    fun getCanchasByName(nombre: String): List<CanchaEntity>

//    @Query("SELECT * FROM CanchasDB WHERE Nombre LIKE '%:name%'")
//    fun getByName(name:String):List<CanchaEntity>
}

