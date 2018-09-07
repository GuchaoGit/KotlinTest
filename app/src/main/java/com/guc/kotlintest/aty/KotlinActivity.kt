package com.guc.kotlintest.aty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guc.kotlintest.R
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * Created by guc on 2018/9/7.
 * 描述：
 */
class KotlinActivity : AppCompatActivity(){
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_kotlin)
        tv_show.text="Hello Kotlin"
    }
}