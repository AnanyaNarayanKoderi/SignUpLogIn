package com.example.signuplogin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE username == :usr")
    fun getUser(usr: String): User

    @Insert
    fun insertuser(user: User)
}