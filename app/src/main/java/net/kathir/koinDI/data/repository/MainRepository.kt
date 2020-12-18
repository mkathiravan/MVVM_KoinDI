package net.kathir.koinDI.data.repository

import net.kathir.koinDI.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper)
{
    suspend fun getUsers() = apiHelper.getUsers()
}
