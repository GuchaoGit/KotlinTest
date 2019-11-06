package com.guc.kotlintest.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.media.MediaMetadataRetriever
import android.os.Build
import android.util.Base64
import android.view.WindowManager
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by guc on 2018/9/7.
 * 描述：工具类
 */
object Utils {

    //region  屏幕相关
    /**
     * 获取屏幕宽度 px
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var point = Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.x
    }

    /**
     * 获取屏幕高度 px
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var point = Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.y
    }

    /**
     * 设置全屏
     */
    fun setFullScreen(aty: Activity) {
        aty.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    /**
     * 设置横屏
     */
    fun setLandscape(aty: Activity) {
        aty?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    /**
     * 设置竖屏
     */
    fun setPortrait(aty: Activity) {
        aty?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /**
     * 判断是否为横屏
     */
    fun isLandscape(context: Context): Boolean {
        return context?.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * 设置窗口透明度
     */
    fun setWindowAlfa(activity: Activity, alpha: Float) {
        if (alpha < 0 || alpha > 1) return
        val windowLP = activity.window.attributes
        windowLP.alpha = alpha
        if (alpha == 1f) {//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        } else {//此行代码主要是解决在华为手机上半透明效果无效的bug
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        activity.window.attributes = windowLP
    }
    //endregion

    //region 文件
    /**
     * 保存bitmap到本地
     */
    fun saveBitmap(context: Context, bitmap: Bitmap, filePath: String): String {
        var file: File? = null
        try {
            file = File(filePath)
            if (!file.exists()) {
                file.parentFile.mkdir()
                file.createNewFile()
            }
            var fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return if (file?.absolutePath == null) "" else file.absolutePath
    }

    /**
     * bitmap 转为Base64字符串
     */
    fun bitmap2String(bitmap: Bitmap, quality: Int): String? {
        var bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        var bytes = bos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * String 转为 bitmap
     */
    fun string2Bitmap(strPic: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            var bitmapArray = Base64.decode(strPic, Base64.DEFAULT) as ByteArray
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
        }
        return bitmap
    }

    /**
     * 将信息写入文件
     */
    fun writeMsg2File(filePath: String, content: String, append: Boolean): Boolean {
        var file: File? = null
        try {
            file = File(filePath)
            if (!file.exists()) {
                file.parentFile.mkdir()
                file.createNewFile()
            }
            if (append) {
                file.appendText(content)
            } else {
                file.writeText(content)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
        }
        return true
    }

    //endregion

    //region 日期处理相关
    /**
     * 日期对象 转为 时间字符串
     */
    fun date2Str(date: Date?, pattern: String): String {
        return SimpleDateFormat(pattern).format(date)
    }

    /**
     * 将字符串 转为 日期对象
     */
    fun str2Date(dateStr: String, pattern: String): Date? {
        var date: Date? = null
        try {
            date = SimpleDateFormat(pattern).parse(dateStr)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    /**
     * 日期是否在当前之前
     */
    fun isBeforeDate(date: Date?): Boolean {
        val curDate = Date()
        return date?.before(curDate)!!
    }

    /**
     * 时间毫秒转为时间格式
     */
    fun timeMillis2Str(time: Long, pattern: String): String {
        try {
            val date = Date(time)
            return date2Str(date, pattern)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return String().plus(time)

    }

    /**
     * 时间毫秒转为时间格式
     */
    fun timeMillis2Str(time: String, pattern: String): String {
        val timeMillis: Long = time?.toLong()
        return timeMillis2Str(timeMillis, pattern)
    }

    /**
     * 时间格式转换
     */
    fun timeTranslate(originpattern: String, destPattern: String, time: String): String {
        val sdf = SimpleDateFormat(originpattern)
        var date: Date
        try {
            date = sdf.parse(time)
        } catch (e: Exception) {
            return time
        }
        return date2Str(date, destPattern)
    }
    //endregion

    //region 文件处理相关

    /**
     * 删除文件夹
     */
    fun deleteDir(dir: File): Boolean {
        if (dir == null) return false
        if (!dir.exists()) return true
        if (!dir.isDirectory) return false
        val files: Array<File> = dir.listFiles()
        if (files != null && files?.size > 0) {
            for (file in files) {
                if (file.isFile) {
                    if (!file.delete()) return false
                } else if (file.isDirectory) {
                    if (!deleteDir(file)) return false
                }
            }
        }
        return dir.delete()
    }

    /**
     * 获取文件的MineType类型
     */
    fun getMineType(filePath: String): String {
        val mmr = MediaMetadataRetriever()
        var mine = "text/plain"
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath)
                mine = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
            } catch (e: Exception) {
            }
        }
        return mine
    }
    //endregion


}