package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="topic")
data class Topic(@PrimaryKey val topicID: String, val topicTitle: String, val topicContent: String,
                 val userTel: String) {
}