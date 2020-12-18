package net.kathir.koinDI.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kathir.koinDI.data.model.User
import net.kathir.koinDI.data.repository.MainRepository
import net.kathir.koinDI.utils.NetworkHelper
import net.kathir.koinDI.utils.Resource

class MainViewModel(private val mainRepository: MainRepository, private val networkHelper: NetworkHelper) : ViewModel()
{
    private val _users = MutableLiveData<Resource<List<User>>>()

    val users: LiveData<Resource<List<User>>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if(networkHelper.isNetworkConnected())
            {
                mainRepository.getUsers().let {
                    if(it.isSuccessful)
                    {
                        _users.postValue(Resource.success(it.body()))
                    }
                    else _users.postValue(Resource.error(it.errorBody().toString(),null))
                }
            }
            else _users.postValue(Resource.error("No internet connection", null))
        }
    }


}