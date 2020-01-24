package com.example.metalcalculator.Custom

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.metalcalculator.R

class CalculationDialog(
    l1: String,
    l2: String,
    l3: String,
    l4: String,
    v1: String,
    v2: String,
    v3: String,
    v4: String
) : DialogFragment() {

    private val L1 = l1
    private val L2 = l2
    private val L3 = l3
    private val L4 = l4

    private val V1 = v1
    private val V2 = v2
    private val V3 = v3
    private val V4 = v4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.layout_calculation, container, false)
        INITIALIZE(v)
        return v
    }

    private fun INITIALIZE(v: View) {
        val linear_3 = v.findViewById<LinearLayout>(R.id.linear_3)
        val linear_4 = v.findViewById<LinearLayout>(R.id.linear_4)

        val txt_1 = v.findViewById<TextView>(R.id.txt_1)
        val txt_2 = v.findViewById<TextView>(R.id.txt_2)
        val txt_3 = v.findViewById<TextView>(R.id.txt_3)
        val txt_4 = v.findViewById<TextView>(R.id.txt_4)

        val txt_1_ans = v.findViewById<TextView>(R.id.txt_1_ans)
        val txt_2_ans = v.findViewById<TextView>(R.id.txt_2_ans)
        val txt_3_ans = v.findViewById<TextView>(R.id.txt_3_ans)
        val txt_4_ans = v.findViewById<TextView>(R.id.txt_4_ans)

        if (L3 == "" && V3 == "") {
            linear_3.visibility = View.GONE
            linear_4.visibility = View.GONE
        } else {
            linear_3.visibility = View.VISIBLE
            linear_4.visibility = View.VISIBLE
        }

        txt_1.text = L1
        txt_2.text = L2
        txt_3.text = L3
        txt_4.text = L4

        txt_1_ans.text = V1
        txt_2_ans.text = V2
        txt_3_ans.text = V3
        txt_4_ans.text = V4
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val metrics = DisplayMetrics()
            activity!!.windowManager.defaultDisplay.getMetrics(metrics)
            if (dialog!!.window != null) {
                dialog!!.window!!.setGravity(Gravity.BOTTOM)
                dialog!!.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
            }
        }
    }
}