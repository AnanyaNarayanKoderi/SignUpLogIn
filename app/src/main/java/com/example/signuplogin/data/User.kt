package com.example.signuplogin.data

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "Password") val password: String?
)