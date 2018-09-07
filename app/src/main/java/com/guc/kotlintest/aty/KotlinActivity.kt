package com.guc.kotlintest.aty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.guc.kotlintest.R
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * Created by guc on 2018/9/7.
 * 描述：
 */
class KotlinActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_kotlin)
        tv_show.text="Hello Kotlin"

        tv_show.setOnClickListener(this)
        tv_show_2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_show -> Toast.makeText(this, "点击了按钮1", Toast.LENGTH_LONG).show()
            R.id.tv_show_2 -> Toast.makeText(this, "点击了按钮2", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, "点击了错了", Toast.LENGTH_LONG).show()
        }
    }
}