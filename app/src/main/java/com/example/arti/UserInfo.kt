package com.example.article

import android.os.Parcel
import android.os.Parcelable


// 유저 정보 생성 데이터 클래스
data class User (
    var Id: String, var nickname: String,
    var startupField: String, var password: String) {
}

// users[0].password

object UserManager {
    val users = mutableListOf<User>()
    fun createUser(Id: String, nickname: String, startupField: String, password: String) {
        users.add(User(Id, nickname, startupField, password))
    }
    fun findUser(Id_: String): User? {
        return users.find { it.Id == Id_ }
    }
}