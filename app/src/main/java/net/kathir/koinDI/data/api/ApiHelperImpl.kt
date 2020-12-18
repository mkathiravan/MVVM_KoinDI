package net.kathir.koinDI.data.api

import net.kathir.koinDI.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper
{
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers();

}