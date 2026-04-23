package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    fun buscarTodos(): List<Usuario>

    @Insert
    suspend fun inserirUsuario(vararg usuario: Usuario)


}