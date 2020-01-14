package com.lulu.dialogdemo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    private val topDialogFragment = TopDialogFragment()
    private val bottomDialogFragment = BottomDialogFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener{
            topDialogFragment.show(supportFragmentManager)
            bottomDialogFragment.show(supportFragmentManager)
            topDialogFragment.setOnDialogCancelListener {
                if (bottomDialogFragment.isVisible) {
                    bottomDialogFragment.dismiss()
                }
            }
        }
    }
}
