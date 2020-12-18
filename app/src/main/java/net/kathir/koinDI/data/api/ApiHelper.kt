package net.kathir.koinDI.data.api

import net.kathir.koinDI.data.model.User
import retrofit2.Response

interface ApiHelper
{
    suspend fun getUsers() : Response<List<User>>
}