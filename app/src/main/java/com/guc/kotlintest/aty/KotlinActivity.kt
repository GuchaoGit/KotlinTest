package com.guc.kotlintest.aty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.guc.kotlintest.R
import com.guc.kotlintest.utils.Utils
import kotlinx.android.synthetic.main.activity_kotlin.*
import java.util.*

// 1. 顶层声明
const val NUM_A: String = "常量"

/**
 * Created by guc on 2018/9/7.
 * 描述：
 */
class KotlinActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mTvShow3: TextView//lateinit 后期初始化 不能声明基本数据 类型

    // 3. 伴生对象中
    companion object {
        const val NUM_C = "伴生对象中声明"
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_kotlin)
        tv_show.text = "Hello Kotlin"

        val time = Date().time
        tv_show_2.text = Utils.timeMillis2Str(time, "yyyy年MM月dd日 HH:mm")

        mTvShow3 = findViewById(R.id.tv_show_3)
        tv_show.setOnClickListener(this)
        tv_show_2.setOnClickListener(this)
        mTvShow3.setOnClickListener(this)
        mTvShow3.text = mStr + NUM_C
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_show -> Toast.makeText(this, tv_show.text.toString(), Toast.LENGTH_LONG).show()
            R.id.tv_show_2 -> Toast.makeText(this, tv_show_2.text.toString(), Toast.LENGTH_LONG).show()
            else -> {
                Toast.makeText(this, "点击了错了", Toast.LENGTH_LONG).show()
                KotlinBaseOperator.jump(this)
            }
        }
    }

    // 声明一个延迟初始化的字符串 必须是只读变量 val 程序第一次使用的时候再初始化
    private val mStr: String by lazy {
        "我是延迟初始化字符串变量"
    }
}

//2. 在object修饰的类中
object TestConst {
    const val NUM_B = "object修饰的类中"
}