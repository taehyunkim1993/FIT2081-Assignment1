package com.example.assignment.model

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

enum class FoodCategory(val value: String) {
    FRUITS("Fruits"),
    VEGETABLES("Vegetables"),
    GRAINS("Grains"),
    RED_MEAT("Red Meat"),
    SEAFOOD("Seafood"),
    POULTRY("Poultry"),
    FISH("Fish"),
    EGGS("Eggs"),
    NUTS_SEEDS("Nuts/Seeds")
}

fun saveFoodCategoriesSharedPref(context: Context, categories: List<FoodCategory>) {
    val pref: SharedPreferences = context.getSharedPreferences("foodPref", Context.MODE_PRIVATE)

    val allCategories = mutableSetOf<String>()
    categories.forEach { category -> allCategories.add(category.value)}

    pref.edit {
        putStringSet("foodCategories", allCategories)
        apply()
    }
}

fun getFoodCategorySharedPref(context: Context) : Set<FoodCategory>? {
    val prefs: SharedPreferences = context.getSharedPreferences("foodPref", Context.MODE_PRIVATE)
    val categories = prefs.getStringSet("foodCategories", null)

    return categories?.mapNotNull {
        value -> FoodCategory.entries.find { it.value == value }}?.toSet()
}

