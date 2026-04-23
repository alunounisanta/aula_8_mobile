package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dao.UsuarioDao
import com.example.myapplication.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

}