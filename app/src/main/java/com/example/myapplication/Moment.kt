package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "topic")
data class Moment(
    @PrimaryKey val topicID: String?,
    val topicTitle:String,val topicContent:String){

}