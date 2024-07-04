package com.example.article

class UserInfo(nickname: String, startupField: String, password: String, passwordCheck: String ) {
    var nickname: String
    var startupField: String
    var password: String
    var passwordCheck: String

    init{
        this.nickname = nickname
        this.startupField = startupField
        this.password = password
        this.passwordCheck = passwordCheck
    }



}