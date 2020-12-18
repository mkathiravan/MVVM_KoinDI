package net.kathir.koinDI.data.api

import net.kathir.koinDI.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService
{
    @GET("users")
    suspend fun getUsers() : Response<List<User>>
}