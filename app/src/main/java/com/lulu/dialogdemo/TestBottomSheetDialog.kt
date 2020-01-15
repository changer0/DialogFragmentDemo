package com.lulu.dialogdemo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * @author zhanglulu on 2020/1/15.
 * for 测试底部弹窗
 */
class TestBottomSheetDialog: BaseDialogFragment() {
    init {
        gravity = Gravity.BOTTOM
        isDimBehind = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //R.style.BottomSheetDialogStyle 指定样式
        val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogStyle)
        dialog.setContentView(R.layout.layout_dialog_bottom_sheet)
        //添加悬浮按钮
        var bottomSheet = dialog.findViewById<FrameLayout>(R.id.container)
        var floatBtnView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_bottom_float, null)
        bottomSheet?.addView(floatBtnView!!, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM))
        val closeBt = floatBtnView.findViewById<Button>(R.id.close)
        closeBt?.setOnClickListener{
            dismiss()
        }
        return dialog
    }

}