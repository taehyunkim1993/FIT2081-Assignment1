package com.example.assignment.data

import android.content.Context
import com.example.assignment.model.ScoreIndex
import com.example.assignment.model.Scores
import com.example.assignment.model.User

class UserRepository(val context: Context) {

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

                    val gender: Int = if (userData[2] == "Male") 0 else 1
                    val scores = Scores(HEIFAtotal = userData[ScoreIndex.HEIFAtotalIndex.index + gender].toFloat(),
                        Vegetables = userData[ScoreIndex.VegetablesIndex.index + gender].toFloat(),
                        Fruits = userData[ScoreIndex.FruitsIndex.index + gender].toFloat(),
                        GrainsAndCereals = userData[ScoreIndex.GrainsAndCerealsIndex.index + gender].toFloat(),
                        Wholegrains = userData[ScoreIndex.WholegrainsIndex.index + gender].toFloat(),
                        MeatAndAlternatives = userData[ScoreIndex.MeatAndAlternativesIndex.index + gender].toFloat(),
                        Dairy = userData[ScoreIndex.DairyIndex.index + gender].toFloat(),
                        Water = userData[ScoreIndex.WaterIndex.index + gender].toFloat(),
                        UnsaturatedFats = userData[ScoreIndex.UnsaturatedFatsIndex.index + gender].toFloat(),
                        Sodium = userData[ScoreIndex.SodiumIndex.index + gender].toFloat(),
                        Sugar = userData[ScoreIndex.SugarIndex.index + gender].toFloat(),
                        Alcohol = userData[ScoreIndex.AlcoholIndex.index + gender].toFloat(),
                        Discretionary = userData[ScoreIndex.DiscretionaryIndex.index + gender].toFloat()
                        )

                    users.add(User(id = userData[1], phoneNumber = userData[0], scores = scores))
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

    fun getUserWithUserId(id: String): User? {
        return userList.find { it.id == id }
    }
}