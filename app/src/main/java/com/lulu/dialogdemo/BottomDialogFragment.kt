package com.lulu.dialogdemo

import android.os.Bundle
import android.view.*

/**
 * @author zhanglulu on 2020/1/13.
 * for BottomDialogFragment 顶部弹窗测试
 */
class BottomDialogFragment : BaseDialogFragment() {

    init {
        //让其没有焦点，这样才能同时显示两个弹窗
        isHasFocusable = false
        gravity = Gravity.BOTTOM
        windowAnimStyleRes = R.style.Animation_menuAnim
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.layout_dialog, null)

        return rootView
    }
}