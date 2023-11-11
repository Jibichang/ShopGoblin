package com.wa.shopgoblin.ui.main

import androidx.lifecycle.ViewModel
import com.wa.shopgoblin.data.database.user.User
import com.wa.shopgoblin.data.database.user.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val username: StateFlow<String> = _userName

    private fun sayHello(user: User): String {
        return  "${user.firstName} ${user.lastName}"
    }

    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            val firstName = "Warunee"
            val lastName = "Khammak"

//            val entities = User(firstName = firstName, lastName = lastName)
//            userDao.insertAll(entities)

            val user = userDao.findByName(firstName, lastName)

            _userName.value = sayHello(user)
        }
    }
}