package com.guc.kotlintest.bean

/**
 * Created by guc on 2018/9/7.
 * 描述：
 */
class User {
    var id: String? = null
    var name: String? = null
    var age: Int? = null

    constructor(name: String, age: Int?) {
        this.name = name
        this.age = age
    }

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return name + getAge()
    }

    fun getAge(): String {
        return if (age == null) "" else age.toString()
    }
}
