package com.guc.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.guc.kotlintest.aty.KotlinActivity
import com.guc.kotlintest.bean.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        tv_show_main.setOnClickListener(View.OnClickListener {
            jump(tv_show_main)
        })
    }

    private fun initView() {
        val user = User("guc")
        tv_show_main.setText(user.toString())
    }

    fun jump(view: View) {
        val intent = Intent()
        //获取intent对象
        intent.setClass(this, KotlinActivity::class.java)
        // 获取class是使用::反射
        startActivity(intent)
    }
}
