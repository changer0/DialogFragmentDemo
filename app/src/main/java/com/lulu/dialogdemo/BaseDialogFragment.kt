package com.lulu.dialogdemo

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * @author zhanglulu on 2020/1/14.
 * for DialogFragment 基类 的，封装一些通用的接口和方法 <br/>
 * 
 * DialogFragment 生命周期：
 * show -> onCreate -> onCreateDialog -> onActivityCreated -> onStart -> 显示 Dialog -> onStop -> onDestroy
 */
private const val TAG = "BaseDialogFragment"

open class BaseDialogFragment : DialogFragment() {
    /**
     * 取消监听
     */
     private var onDialogDismissListener: (() -> Unit)? = null
    /**
     *是否有焦点
     */
    public var isHasFocusable = true

    /**
     * 显示位置
     */
    public var gravity: Int = Gravity.CENTER

    /**
     * 弹窗动画
     */
    @StyleRes
    public var windowAnimStyleRes: Int? = null

    /**
     * 是否需要背景变暗
     */
    public var isDimBehind: Boolean = false

    /**
     * 背景变暗之后的透明度，当且仅当 isDimBehind=true 时生效 <br/>
     */
    @FloatRange(from= 0.0, to=1.0)
    public var dimBehindAlpha: Float = 0.5F

    /**
     * 是否全屏
     */
    public var isFullScreen = false

    /**
     * 是否正在展示
     */
    public var isShowing = false

    /**
     * 当前 Dialog 的 Window
     */
    public var window: Window? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        window = dialog.window
        window?.let {
            it.setGravity(gravity)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (!isDimBehind) {
                it.attributes.flags =
                    //inv 取非 (~)
                    it.attributes.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            }
            if (!isHasFocusable) {
                it.attributes.flags = it.attributes.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            }
            if (isFullScreen) {
                it.attributes.flags = it.attributes.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
                it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            } else {
                it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            it.attributes.dimAmount = dimBehindAlpha
            windowAnimStyleRes?.let { it1 -> it.setWindowAnimations(it1) }
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        isShowing = false
        super.onDismiss(dialog)
        onDialogDismissListener?.invoke()
    }

    public fun setOnDialogDismissListener(listener: () -> Unit)  {
        onDialogDismissListener = listener
    }


    /**
     * 请使用该方法显示Dialog
     * 使用其他 show 方法可能会引起 Fragment Already add 异常
     */
    public fun show(fragmentManager: FragmentManager) {
        if (isAdded) {
            return
        }
        isShowing = true
        show(fragmentManager, javaClass.name)
    }


}