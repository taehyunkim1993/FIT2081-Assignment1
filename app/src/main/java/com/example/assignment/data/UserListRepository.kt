package com.example.assignment.data

import android.content.Context
import com.example.assignment.model.User

class UserListRepository(val context: Context) {

    val userList: List<User> by lazy {
        parseUsersFromCSV()
    }

    private fun parseUsersFromCSV(): List<User> {
        val users = mutableListOf<User>()

        try {
            val inputStream = context.assets.open("userdata.csv")

            inputStream.bufferedReader().useLines { lines ->
                lines.drop(1).forEach { line ->
                    val userData = line.split(",").map({ it.trim() })
                    if (userData.size >= 2) {
                        users.add(User(id = userData[1], phoneNumber = userData[0]))
                    }
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return users.toList() // to immutable list
    }

    fun validateUser(id: String, phoneNumber: String): Boolean {
        return userList.any { it.id == id && it.phoneNumber == phoneNumber }
    }
}