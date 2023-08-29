package com.app.canchas.data.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.canchas.data.dao.CanchasDao
import com.app.canchas.data.entities.CanchaEntity

@Database( entities = [CanchaEntity::class], version = 1, exportSchema = false
)
abstract class CanchasDb: RoomDatabase() {
    abstract fun canchasDao(): CanchasDao
}