package com.lulu.dialogdemo

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast

/**
 * @author zhanglulu on 2020/1/13.
 * for TopDialogFragment 顶部弹窗测试
 */
class TopDialogFragment : BaseDialogFragment() {
    private val fullScreenDialog = FullScreenDialog()
    private val testBottomSheetDialog = TestBottomSheetDialog()
    init {
        gravity = Gravity.TOP
        windowAnimStyleRes = R.style.Animation_topbarAnim
        isDimBehind = false
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.layout_dialog, null)
        val leftBtn = rootView.findViewById<Button>(R.id.left_btn)
        leftBtn.setOnClickListener{
            Toast.makeText(context, "上左按钮点击", Toast.LENGTH_SHORT).show()
            fragmentManager?.let { it1 -> fullScreenDialog.show(it1) }
        }

        val rightBtn = rootView.findViewById<Button>(R.id.right_btn)
        rightBtn.setOnClickListener{
            Toast.makeText(context, "上右按钮点击", Toast.LENGTH_SHORT).show()
            fragmentManager?.let { it1 -> testBottomSheetDialog.show(it1) }
        }
        return rootView
    }


}