package com.bins.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.UserResponseEntity
import com.bins.domain.usecases.UserInfoUseCase
import com.bins.presentation.base.BaseViewModel
import com.bins.presentation.base.Mapper
import com.bins.presentation.entity.Data
import com.bins.presentation.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val getUserInfoUseCase: UserInfoUseCase,
                     private val mapper: Mapper<DataEntity<UserResponseEntity>, Data<User>>) : BaseViewModel() {


    var mUsers = MutableLiveData<Data<User>>()

    fun fetchUsers() {
        launch {
            val news = getUserInfoUseCase.getUserData()
            news.consumeEach { response ->
                val mappedResponse = mapper.mapFrom(response)
                //Switching the context to main
                withContext(Dispatchers.Main) {
                    mUsers.postValue(mappedResponse)
                }
            }
        }
    }

    fun getUSerLiveData() = mUsers
}