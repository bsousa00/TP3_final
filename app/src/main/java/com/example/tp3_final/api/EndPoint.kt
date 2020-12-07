package com.example.tp3_final.api

import retrofit2.Call
import retrofit2.http.*


interface EndPoints {

    @GET("/users/")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @GET("/myslim/api/problema")
    fun getProblemas(): Call<List<Problema>>



    @FormUrlEncoded
    @POST("/myslim/api/user")
    fun postLogin(@Field("utilizador") utilizador: String?,
                    @Field("password") password: String?): Call<OutputPost>
}