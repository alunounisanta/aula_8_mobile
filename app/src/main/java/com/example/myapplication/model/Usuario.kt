package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "primeiro_nome")
    val primeiroNome:String,
    @ColumnInfo(name = "ultimo_nome")
    val ultimoNome:String,
)
