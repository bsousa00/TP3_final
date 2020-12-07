package com.example.tp3_final.api


data class User(
        val id: Int,
        val utilizador: String,
        val password: String
)

data class Problema(
        val id: Int,
        val lat: String,
        val lng: String,
        val descr: String,
        val iduser: Int
)