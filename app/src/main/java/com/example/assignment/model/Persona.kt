package com.example.assignment.model

import android.app.Person
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.DrawableRes
import androidx.core.content.edit
import com.example.assignment.R

enum class Persona(val value: String, val description: String, @DrawableRes val imageResId: Int) {
    HEALTH_DEVOTEE(value = "Health Devotee",
        description =  "I’m passionate about healthy eating & health plays a big part in my life. I use social media to follow active lifestyle personalities or get new recipes/exercise ideas. I may even buy superfoods or follow a particular type of diet. I like to think I am super healthy.",
    imageResId = R.drawable.persona_1),
    MINDFUL_EATER(value = "Mindful Eater",
        description = "I’m health-conscious and being healthy and eating healthy is important to me. Although health means different things to different people, I make conscious lifestyle decisions about eating based on what I believe healthy means. I look for new recipes and healthy eating information on social media.",
        imageResId = R.drawable.persona_2),
    WELLNESS_STRIVER(value = "Wellness Striver",
        description = "I aspire to be healthy (but struggle sometimes). Healthy eating is hard work! I’ve tried to improve my diet, but always find things that make it difficult to stick with the changes. Sometimes I notice recipe ideas or healthy eating hacks, and if it seems easy enough, I’ll give it a go.",
        imageResId = R.drawable.persona_3),
    BALANCE_SEEKER(value = "Balance Seeker",
        description =  "I try and live a balanced lifestyle, and I think that all foods are okay in moderation. I shouldn’t have to feel guilty about eating a piece of cake now and again. I get all sorts of inspiration from social media like finding out about new restaurants, fun recipes and sometimes healthy eating tips.",
        imageResId = R.drawable.persona_4),
    HEALTH_PROCRASTINATOR(value = "Health Procrastinator",
        description = "I’m contemplating healthy eating but it’s not a priority for me right now. I know the basics about what it means to be healthy, but it doesn’t seem relevant to me right now. I have taken a few steps to be healthier but I am not motivated to make it a high priority because I have too many other things going on in my life.",
        imageResId = R.drawable.persona_5),
    FOOD_CAREFREE(value = "Food Carefree",
        description = "I’m not bothered about healthy eating. I don’t really see the point and I don’t think about it. I don’t really notice healthy eating tips or recipes and I don’t care what I eat.",
        imageResId = R.drawable.persona_6)
}