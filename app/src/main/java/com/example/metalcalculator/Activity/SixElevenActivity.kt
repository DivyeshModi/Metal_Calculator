package com.example.metalcalculator.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.metalcalculator.Custom.CalculationDialog
import com.example.metalcalculator.Custom.Config
import com.example.metalcalculator.R
import kotlinx.android.synthetic.main.activity_six_eleven.*
import java.text.DecimalFormat

class SixElevenActivity : AppCompatActivity() {

    private var ac: Activity? = null
    private var Selected_Shape_position = 0
    private var Selected_Sub_Shape_position: String? = null

    private var mDialogNos: AlertDialog? = null

    private var No_List = arrayOf(
        "5",
        "5,5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "12",
        "14а",
        "14б",
        "16а",
        "16б",
        "18а",
        "18б",
        "20а",
        "20б",
        "22а",
        "22б",
        "24а",
        "24б"
    )
    private var DensityList = doubleArrayOf(
        2.25,
        2.73,
        3.36,
        3.98,
        4.58,
        5.52,
        6.76,
        8.75,
        11.05,
        13.23,
        14.08,
        16.6,
        17.41,
        20.24,
        21.47,
        24.6,
        25.75,
        29.2,
        30.42,
        34.18
    )

    private var DENSITY = 2.25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_eleven)

        INITIALIZE()
        CONTROL_LISTENER()

    }

    private fun INITIALIZE() {
        ac = this@SixElevenActivity
        Selected_Shape_position = intent.getIntExtra("Selected_Shape_position", 0)

        txt_Unit_Length.text = resources.getString(R.string.Unit_Meter)
        txt_Unit_Weight.text = resources.getString(R.string.Unit_kg)
        when (Selected_Shape_position) {
            5 -> {
                mImageView.setImageResource(R.drawable.vec_shape_6)
                txt_layout_No.hint = resources.getString(R.string.No)
                No_List = arrayOf(
                    "5",
                    "5,5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10",
                    "12",
                    "14а",
                    "14б",
                    "16а",
                    "16б",
                    "18а",
                    "18б",
                    "20а",
                    "20б",
                    "22а",
                    "22б",
                    "24а",
                    "24б"
                )
                DensityList = doubleArrayOf(
                    2.25,
                    2.73,
                    3.36,
                    3.98,
                    4.58,
                    5.52,
                    6.76,
                    8.75,
                    11.05,
                    13.23,
                    14.08,
                    16.6,
                    17.41,
                    20.24,
                    21.47,
                    24.6,
                    25.75,
                    29.2,
                    30.42,
                    34.18
                )
                edt_No.setText(No_List.get(0))
                DENSITY = DensityList.get(0)
            }
            10 -> {
                mImageView.setImageResource(R.drawable.vec_shape_11)
                txt_layout_No.hint = resources.getString(R.string.No)
                No_List = arrayOf(
                    "6",
                    "8",
                    "10",
                    "12",
                    "14",
                    "16",
                    "18",
                    "20",
                    "22",
                    "25",
                    "28",
                    "32",
                    "36",
                    "40",
                    "45",
                    "50",
                    "55",
                    "60",
                    "70",
                    "80"
                )
                DensityList = doubleArrayOf(
                    0.222,
                    0.395,
                    0.617,
                    0.888,
                    1.21,
                    1.58,
                    2.0,
                    2.47,
                    2.98,
                    3.85,
                    4.83,
                    6.31,
                    7.99,
                    9.87,
                    12.48,
                    15.41,
                    18.65,
                    22.19,
                    30.21,
                    39.46
                )
                edt_No.setText(No_List.get(0))
                DENSITY = DensityList.get(0)
            }
            else -> {
                Selected_Sub_Shape_position = intent.getStringExtra("Selected_Sub_Shape_position")
                when {
                    Selected_Sub_Shape_position.equals("1_1") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_2_2_1)
                        txt_layout_No.hint = resources.getString(R.string.dxs)
                        No_List = Config.No_List_1_1
                        DensityList = Config.DensityList_1_1
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("4_4") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_5_5_5)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_4_4
                        DensityList = Config.DensityList_4_4
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_1") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_2_1)
                        txt_layout_No.hint = resources.getString(R.string.Axs)
                        No_List = Config.No_List_6_1
                        DensityList = Config.DensityList_6_1
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_2") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_3_1)
                        txt_layout_No.hint = resources.getString(R.string.AxBxs)
                        No_List = Config.No_List_6_2
                        DensityList = Config.DensityList_6_2
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_3") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_2_1)
                        txt_layout_No.hint = resources.getString(R.string.AxAxs)
                        No_List = Config.No_List_6_3
                        DensityList = Config.DensityList_6_3
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_4") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_3_1)
                        txt_layout_No.hint = resources.getString(R.string.AxBxs)
                        No_List = Config.No_List_6_4
                        DensityList = Config.DensityList_6_4
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_5") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_2_1)
                        txt_layout_No.hint = resources.getString(R.string.AxAxs)
                        No_List = Config.No_List_6_5
                        DensityList = Config.DensityList_6_5
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_6") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_3_1)
                        txt_layout_No.hint = resources.getString(R.string.AxBxs)
                        No_List = Config.No_List_6_6
                        DensityList = Config.DensityList_6_6
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_7") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_2_1)
                        txt_layout_No.hint = resources.getString(R.string.AxAxs)
                        No_List = Config.No_List_6_7
                        DensityList = Config.DensityList_6_7
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_8") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_3_1)
                        txt_layout_No.hint = resources.getString(R.string.AxBxs)
                        No_List = Config.No_List_6_8
                        DensityList = Config.DensityList_6_8
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_9") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_2_1)
                        txt_layout_No.hint = resources.getString(R.string.AxAxs)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_6_9
                        DensityList = Config.DensityList_6_9
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("6_10") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_7_3_1)
                        txt_layout_No.hint = resources.getString(R.string.AxBxs)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_6_10
                        DensityList = Config.DensityList_6_10
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_1") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_2_1)
                        txt_layout_No.hint = resources.getString(R.string.Noxt)
                        No_List = Config.No_List_7_1
                        DensityList = Config.DensityList_7_1
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_2") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_3_1)
                        txt_layout_No.hint = resources.getString(R.string.Noxt)
                        No_List = Config.No_List_7_2
                        DensityList = Config.DensityList_7_2
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_3") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_2_1)
                        txt_layout_No.hint = resources.getString(R.string.bxbxt)
                        No_List = Config.No_List_7_3
                        DensityList = Config.DensityList_7_3
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_4") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_3_1)
                        txt_layout_No.hint = resources.getString(R.string.Bxbxt)
                        No_List = Config.No_List_7_4
                        DensityList = Config.DensityList_7_4
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_5") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_2_1)
                        txt_layout_No.hint = resources.getString(R.string.bxbxt)
                        No_List = Config.No_List_7_5
                        DensityList = Config.DensityList_7_5
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("7_6") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_8_3_1)
                        txt_layout_No.hint = resources.getString(R.string.Bxbxt)
                        No_List = Config.No_List_7_6
                        DensityList = Config.DensityList_7_6
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_1") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_1
                        DensityList = Config.DensityList_8_1
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_2") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_3_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_2
                        DensityList = Config.DensityList_8_2
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_3") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_3
                        DensityList = Config.DensityList_8_3
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_4") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_5_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_4
                        DensityList = Config.DensityList_8_4
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_5") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_5
                        DensityList = Config.DensityList_8_5
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_6") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_6
                        DensityList = Config.DensityList_8_6
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_7") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_7
                        DensityList = Config.DensityList_8_7
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_8") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_5_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_8
                        DensityList = Config.DensityList_8_8
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_9") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_9
                        DensityList = Config.DensityList_8_9
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_10") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_8_10
                        DensityList = Config.DensityList_8_10
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_11") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_8_11
                        DensityList = Config.DensityList_8_11
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_12") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_12
                        DensityList = Config.DensityList_8_12
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_13") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_13
                        DensityList = Config.DensityList_8_13
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_14") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_14
                        DensityList = Config.DensityList_8_14
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_15") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_3)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_15
                        DensityList = Config.DensityList_8_15
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_16") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_3)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_16
                        DensityList = Config.DensityList_8_16
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_17") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_3_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_17
                        DensityList = Config.DensityList_8_17
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_18") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_8_18
                        DensityList = Config.DensityList_8_18
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_19") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_2)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_19
                        DensityList = Config.DensityList_8_19
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_20") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_20
                        DensityList = Config.DensityList_8_20
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_21") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_21
                        DensityList = Config.DensityList_8_21
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_22") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_6_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_22
                        DensityList = Config.DensityList_8_22
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("8_23") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_9_5_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_8_23
                        DensityList = Config.DensityList_8_23
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_1") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_2_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_1
                        DensityList = Config.DensityList_9_1
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_2") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_3_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_2
                        DensityList = Config.DensityList_9_2
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_3") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_3
                        DensityList = Config.DensityList_9_3
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_4") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_4
                        DensityList = Config.DensityList_9_4
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_5") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_3_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_5
                        DensityList = Config.DensityList_9_5
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_6") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_9_6
                        DensityList = Config.DensityList_9_6
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_7") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        No_List = Config.No_List_9_7
                        DensityList = Config.DensityList_9_7
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_8") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_4_1)
                        txt_layout_No.hint = resources.getString(R.string.No)
                        txt_Unit_Length.text = resources.getString(R.string.Unit_Feet)
                        txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)
                        No_List = Config.No_List_9_8
                        DensityList = Config.DensityList_9_8
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_9") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_1_1)
                        txt_layout_No.hint = resources.getString(R.string.hxb)
                        No_List = Config.No_List_9_9
                        DensityList = Config.DensityList_9_9
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                    Selected_Sub_Shape_position.equals("9_10") -> {
                        mImageView.setImageResource(R.drawable.vec_shape_10_1_1)
                        txt_layout_No.hint = resources.getString(R.string.hxb)
                        No_List = Config.No_List_9_10
                        DensityList = Config.DensityList_9_10
                        edt_No.setText(No_List.get(0))
                        DENSITY = DensityList.get(0)
                    }
                }
            }
        }
        DialogNos()
    }

    private fun CONTROL_LISTENER() {
        relative_main.setOnClickListener { Config.HideKeyboard(ac as SixElevenActivity) }

        btn_Calculate.setOnClickListener { Calculation() }

        edt_No.setOnClickListener { mDialogNos?.show() }

        rg.setOnCheckedChangeListener { _, i ->
            if (i == R.id.rb_length) {
                txt_layout_Length.visibility = View.VISIBLE
                txt_layout_Price_per_1_kg.visibility = View.VISIBLE
                txt_layout_Weight.visibility = View.GONE

                edt_Quantity.imeOptions = EditorInfo.IME_ACTION_NEXT
            } else if (i == R.id.rb_weight) {
                txt_layout_Length.visibility = View.GONE
                txt_layout_Price_per_1_kg.visibility = View.GONE
                txt_layout_Weight.visibility = View.VISIBLE

                edt_Quantity.imeOptions = EditorInfo.IME_ACTION_DONE
            }
        }
    }

    private fun Calculation() {
        if (isValid()) {
            val decimalFormat = DecimalFormat("#.###")
            val decimalFormat2 = DecimalFormat("#.##")
            var L1 = ""
            var L2 = ""
            var L3 = ""
            var L4 = ""
            var V1 = ""
            var V2 = ""
            var V3 = ""
            var V4 = ""

            if (TextUtils.isEmpty(edt_Quantity.text.toString()))
                edt_Quantity.setText("1.0")
            val QUANTITY = edt_Quantity.text.toString().toDouble()

            if (rb_length.isChecked) {
                if (TextUtils.isEmpty(edt_Price_per_1_kg.text.toString()))
                    edt_Price_per_1_kg.setText("0.0")
                val PRICE = edt_Price_per_1_kg.text.toString().toDouble()
                val LENGTH = edt_Length.text.toString().toDouble()

                val WEIGHT_MAIN = (DENSITY * LENGTH)
                val WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY
                val WEIGHT_PRICE = WEIGHT_MAIN * PRICE
                val WEIGHT_QUANTITY_PRICE = WEIGHT_QUANTITY * PRICE

                L1 = "Weight : "
                L2 = "Cost : "
                L3 = "Weight $QUANTITY pcs : "
                L4 = "Cost of $QUANTITY pcs : "

                V1 = "${decimalFormat.format(WEIGHT_MAIN)} kg"
                V2 = "${decimalFormat2.format(WEIGHT_PRICE)} "
                V3 = "${decimalFormat.format(WEIGHT_QUANTITY)} kg"
                V4 = "${decimalFormat2.format(WEIGHT_QUANTITY_PRICE)} "
            } else if (rb_weight.isChecked) {
                val WEIGHT = edt_Weight.text.toString().toDouble()

                val LENGTH_MAIN = WEIGHT / DENSITY
                val LENGTH_QUANTITY = LENGTH_MAIN * QUANTITY

                L1 = "Length : "
                L2 = "Length $QUANTITY pcs : "
                L3 = ""
                L4 = ""

                V1 = "${decimalFormat.format(LENGTH_MAIN)} ${txt_Unit_Length.text}"
                V2 = "${decimalFormat.format(LENGTH_QUANTITY)} ${txt_Unit_Length.text}"
                V3 = ""
                V4 = ""
            }

            CalculationDialog(L1, L2, L3, L4, V1, V2, V3, V4).show(supportFragmentManager, "")
        }
    }

    private fun isValid(): Boolean {
        if (rb_length.isChecked) {
            txt_layout_No?.error = null
            txt_layout_Length?.error = null

            txt_layout_No.isErrorEnabled = false
            txt_layout_Length.isErrorEnabled = false

            return when {
                edt_No?.text?.isEmpty()!! -> {
                    txt_layout_No?.error = resources.getString(R.string.err_msg_Nos)
                    false
                }
                edt_Length?.text?.isEmpty()!! -> {
                    txt_layout_Length?.error = resources.getString(R.string.err_msg_Length)
                    false
                }
                edt_Length.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                    txt_layout_Length?.error = resources.getString(R.string.err_msg_valid_Length)
                    false
                }
                else -> true
            }
        }
        txt_layout_No?.error = null
        txt_layout_Weight?.error = null

        txt_layout_No.isErrorEnabled = false
        txt_layout_Weight.isErrorEnabled = false

        return when {
            edt_No?.text?.isEmpty()!! -> {
                txt_layout_No?.error = resources.getString(R.string.err_msg_Nos)
                false
            }
            edt_Weight?.text?.isEmpty()!! -> {
                txt_layout_Weight?.error = resources.getString(R.string.err_msg_Weight)
                false
            }
            edt_Weight.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                txt_layout_Weight?.error = resources.getString(R.string.err_msg_valid_Weight)
                false
            }
            else -> true
        }
    }

    @SuppressLint("InflateParams")
    private fun DialogNos() {
        val layout: View = LayoutInflater.from(ac).inflate(R.layout.dialog_material, null)
        val alert = ac?.let { AlertDialog.Builder(it) }
        alert?.setCancelable(true)
        alert?.setView(layout)
        mDialogNos = alert?.create()
        mDialogNos?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        mDialogMaterial!!.window!!.attributes.windowAnimations = R.style.DialogAnimation

        val mRecyclerView = layout.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.adapter = NosAdapter()
    }

    internal inner class NosAdapter : RecyclerView.Adapter<NosAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(ac).inflate(R.layout.graphics_values, parent, false)
            return MyViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.SetData(position)
        }

        override fun getItemCount(): Int {
            return No_List.size
        }

        internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var txt: TextView = itemView.findViewById(R.id.txt)

            fun SetData(pos: Int) {
                txt.text = No_List[pos]

                if (pos % 2 == 0) {
                    txt.setBackgroundColor(resources.getColor(R.color.colorLightGray))
                } else {
                    txt.setBackgroundColor(resources.getColor(R.color.colorWhite))
                }

                itemView.setOnClickListener {
                    edt_No.setText(No_List.get(pos))
                    DENSITY = DensityList.get(pos)
                    if (mDialogNos?.isShowing!!)
                        mDialogNos?.dismiss()
                }
            }
        }
    }
}
