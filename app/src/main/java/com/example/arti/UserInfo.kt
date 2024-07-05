package com.example.article

import android.os.Parcel
import android.os.Parcelable


// 유저 정보 생성 데이터 클래스
public data class User (val Id: String, val nickname: String,
        val startupField: String, val password: String, val passwordCheck: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Id)
        parcel.writeString(nickname)
        parcel.writeString(startupField)
        parcel.writeString(password)
        parcel.writeString(passwordCheck)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}