package com.guc.kotlintest.aty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.guc.kotlintest.R

/**
 * Created by guc on 2018/9/10.
 * 描述：
 */
class KotlinBaseOperator : AppCompatActivity() {
    lateinit var mArray1: Array<Int>

    companion object {
        fun jump(context: Context) {
            var intent = Intent(context, KotlinBaseOperator::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base_operator)
        initTestData()
    }

    private fun initTestData() {
        mArray1 = arrayOf(1, 2, 3, 4, 5)
        Log.e("Array(arrayOf)", "开始")
        for (i in mArray1) {
            Log.e("Array(arrayOf)", "$i")
        }
        Log.e("Array(arrayOf)", "结束")

        var arr2 = arrayOfNulls<Int>(3)
        Log.e("Array(arrayOfNulls)", "开始")
        arr2[1] = 20
        for (i in arr2) {
            Log.e("Array(arrayOfNulls)", "$i")
        }
        Log.e("Array(arrayOfNulls)", "结束")

        var arr3 = Array(3, { index -> (index * 3).toString() })
        Log.e("Array()", "开始")
        for (i in arr3) {
            Log.e("Array()", "$i")
        }
        Log.e("Array()", "结束")


    }
}