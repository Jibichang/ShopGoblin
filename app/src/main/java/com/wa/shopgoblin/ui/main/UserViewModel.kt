package com.wa.shopgoblin.ui.main

import androidx.lifecycle.ViewModel
import com.wa.shopgoblin.data.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun sayHello(name: String): String {
        val foundUser = repository.findUser(name)
        return foundUser?.let {
            "Hello '$it' from $this"
        } ?: "User '$name' not found!"
    }
}