package com.fomus.eblood.ui.editeProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fomus.eblood.dao.userDao.UserDao
import com.fomus.eblood.data.User

class EditeProfileViewModel : ViewModel() {

    var user = MutableLiveData<User>().apply {
        value = User()
    }
    var userToCreate = MutableLiveData<User>().apply {
        value = User()
    }
    var api = UserDao()
    fun checkExistingUser(
        userId: String,
        onSuccess: (user: User) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        api.getUser(userId, {
            user.value=it
            onSuccess(it!!)
        }, {
            onFailure(it)
        })
    }

    fun creatreANewUser(
        user: User,
        onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        api.createUser(user, {
            onSuccess()
        }, {
            onFailure(it)
        })
    }
}