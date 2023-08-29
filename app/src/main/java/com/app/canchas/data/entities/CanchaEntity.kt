package com.app.canchas.data.entities

import android.content.Context
import android.content.res.AssetManager
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson


@Entity(tableName = "CanchasDB")
data class CanchaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "Nombre", defaultValue = "")
    val nombre: String?,

    @ColumnInfo(name = "Direccion", defaultValue = "")
    val direccion: String?,

    @ColumnInfo(name = "Barrio", defaultValue = "")
    val barrio: String?,

    @ColumnInfo(name = "Ciudad", defaultValue = "")
    val ciudad: String?,

    @ColumnInfo(name = "Telefono", defaultValue = "")
    val telefono: String?,

    @ColumnInfo(name = "Mail", defaultValue = "")
    val mail: String?,

    @ColumnInfo(name = "CantidadCanchas", defaultValue = "1")
    val cantidadCanchas: String?,

    @ColumnInfo(name = "NumeroCanchas", defaultValue = "")
    val numeroCanchas: String?,

    @ColumnInfo(name = "Tipo", defaultValue = "")
    val tipo: String?
)


