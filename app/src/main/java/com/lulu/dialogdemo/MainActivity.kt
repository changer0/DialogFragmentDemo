package com.lulu.dialogdemo

import android.os.Bundle
import android.widget.Toast
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
            Toast.makeText(this, "topDialogFragment.isShowing : " + topDialogFragment.isShowing, Toast.LENGTH_SHORT).show()
            bottomDialogFragment.show(supportFragmentManager)
            topDialogFragment.setOnDialogDismissListener {
                if (bottomDialogFragment.isShowing) {
                    bottomDialogFragment.dismiss()
                }
                Toast.makeText(this, "topDialogFragment.isShowing : " + topDialogFragment.isShowing, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
