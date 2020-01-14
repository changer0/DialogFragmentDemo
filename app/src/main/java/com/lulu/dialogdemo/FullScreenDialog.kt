package com.lulu.dialogdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

/**
 * @author zhanglulu on 2020/1/14.
 * for 全屏 Dialog
 */
class FullScreenDialog: BaseDialogFragment() {

    init {
        isFullScreen = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.layout_dialog_full, null)
        val leftBtn = rootView.findViewById<Button>(R.id.left_btn)
        leftBtn.text = "这是中间的按钮"
        leftBtn.setOnClickListener{
            Toast.makeText(context, "中左按钮点击", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return rootView
    }
}