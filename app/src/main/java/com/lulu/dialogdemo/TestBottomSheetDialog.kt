package com.lulu.dialogdemo

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
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

    lateinit var viewPager: ViewPager


    /**
     * ###############特别提醒，上下滑动页面需要处理手势冲突############### 参考：BottomSheetDialogListView
     */

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //R.style.BottomSheetDialogStyle 指定样式
        val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogStyle)
        dialog.setContentView(R.layout.layout_dialog_bottom_sheet)

//        val text = dialog.findViewById<TextView>(R.id.tv_text6) as TextView
//        text.text = "我修改了 文字"
        viewPager = dialog.findViewById<ViewPager>(R.id.viewPage) as ViewPager

        //添加悬浮按钮
        var bottomSheet = dialog.findViewById<FrameLayout>(R.id.container)


        bottomSheet?.let {
            bottomSheet.setPadding(bottomSheet.paddingLeft, bottomSheet.paddingTop+40, bottomSheet.paddingRight,bottomSheet.paddingBottom)
            //用来控制距离顶部的距离！
            if (bottomSheet.layoutParams is FrameLayout.LayoutParams) {
                (bottomSheet.layoutParams as FrameLayout.LayoutParams).topMargin = 500
            }

            //bottomSheet.background = context!!.resources.getDrawable(R.drawable.bg_top_corner)
        }
//        var floatBtnView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_bottom_float, null)
//        bottomSheet?.addView(floatBtnView!!, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM))
//        val closeBt = floatBtnView.findViewById<Button>(R.id.close)
//        closeBt?.setOnClickListener{
//            dismiss()
//        }
        return dialog
    }

    private lateinit var mActivity: Activity

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        activity?.let {
            this.mActivity =  activity
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            this.mActivity =  context as Activity
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPager.adapter = MyAdapter(childFragmentManager)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)


    }

    class MyAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        private val listFragment: MutableList<Fragment> = mutableListOf()
        init {
            listFragment.add(TestFragment())
            listFragment.add(TestFragment())
        }
        override fun getItem(position: Int): Fragment {
            return listFragment[position]
        }

        override fun getCount(): Int {
            return 2
        }

    }

}