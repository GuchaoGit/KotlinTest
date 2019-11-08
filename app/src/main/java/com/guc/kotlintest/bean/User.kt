package com.guc.kotlintest.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by guc on 2018/9/7.
 * 描述：
 */
class User(id: String, name: String, age: Int?) : Parcelable {
    var id: String? = null
    var name: String? = null
    var age: Int? = null

    constructor(parcel: Parcel) : this(
            TODO("id"),
            TODO("name"),
            TODO("age")) {
        id = parcel.readString()
        name = parcel.readString()
        age = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    constructor(name: String, age: Int?) : this("", name, age) {
        this.name = name
        this.age = age
    }

    constructor(name: String) : this("", name, 0) {
        this.name = name
    }

    override fun toString(): String {
        return name + getAge()
    }

    fun getAge(): String {
        return if (age == null) "" else age.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeValue(age)
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
