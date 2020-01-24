@file:Suppress("DEPRECATION")

package com.example.metalcalculator.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.metalcalculator.Custom.CalculationDialog
import com.example.metalcalculator.Custom.Config
import com.example.metalcalculator.Database.DataBaseHandler
import com.example.metalcalculator.Model.Model_Material
import com.example.metalcalculator.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_first_four.*
import java.text.DecimalFormat

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class FirstFourActivity : AppCompatActivity() {

    private var ac: Activity? = null
    private var db: DataBaseHandler? = null
    private var Selected_Shape_position = 0
    private var SELECTED_MATERIAL: Model_Material? = null

    private val materialList = ArrayList<Model_Material>()
    private var adp_material: MaterialAdapter? = null
    private var mDialogMaterial: AlertDialog? = null
    private var mDialogAddMaterial: AlertDialog? = null
    private var mDialogDensity: AlertDialog? = null

    private var add_txt_layout_Name: TextInputLayout? = null
    private var add_txt_layout_D1: TextInputLayout? = null
    private var add_txt_layout_D2: TextInputLayout? = null
    private var add_txt_layout_D3: TextInputLayout? = null
    private var add_txt_layout_D4: TextInputLayout? = null
    private var add_txt_layout_D5: TextInputLayout? = null

    private var add_edt_Name: EditText? = null
    private var add_edt_D1: EditText? = null
    private var add_edt_D2: EditText? = null
    private var add_edt_D3: EditText? = null
    private var add_edt_D4: EditText? = null
    private var add_edt_D5: EditText? = null

    private var IS_LARGE_UNIT = false

    private var KD = 1.0
    private var KDV = 1.0
    private var LENGTH_UNIT_VALUE = 0.001
    private var WEIGHT_MAIN = 0.0
    private var WEIGHT_QUANTITY = 0.0
    private var WEIGHT_PRICE = 0.0
    private var WEIGHT_QUANTITY_PRICE = 0.0

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_four)

        INITIALIZE()
        CONTROL_LISTENER()
    }

    private fun INITIALIZE() {
        ac = this@FirstFourActivity
        db = DataBaseHandler(ac as FirstFourActivity)
        Selected_Shape_position = intent.getIntExtra("Selected_Shape_position", 0)

        setLayoutHeight()

        materialList.clear()
        if (!db!!.Is_Empty()) {
            db?.getMaterialList()?.let {
                materialList.addAll(it)

                SELECTED_MATERIAL = Config.getMaterial(ac as FirstFourActivity)
                edt_Name.setText(SELECTED_MATERIAL?.Name)
                edt_Density.setText(SELECTED_MATERIAL?.D_1)
            }
        }
        if (Selected_Shape_position == 0) {
            mImageView.setImageResource(R.drawable.vec_shape_1_1)
            txt_layout_Diameter.hint = resources.getString(R.string.Diameter)
        } else if (Selected_Shape_position == 3) {
            mImageView.setImageResource(R.drawable.vec_shape_4_1)
            txt_layout_Diameter.hint = resources.getString(R.string.S)
        }

        txt_small_unit.setBackgroundResource(R.drawable.bg_blue_white_border)
        txt_small_unit.setTextColor(resources.getColor(R.color.colorWhite))
        txt_large_unit.setBackgroundResource(R.drawable.bg_white_blue_border)
        txt_large_unit.setTextColor(resources.getColor(R.color.colorBlueDark))

        ResetLengthUnit()
        txt_Unit_MM.setBackgroundResource(R.drawable.bg_blue_white_border)
        txt_Unit_MM.setTextColor(resources.getColor(R.color.colorWhite))
        LENGTH_UNIT_VALUE = 0.001

        DialogMaterial()
    }

    private fun CONTROL_LISTENER() {
        relative_main.setOnClickListener { Config.HideKeyboard(ac as FirstFourActivity) }

        btn_Calculate.setOnClickListener {
            Calculation()
        }

        txt_small_unit.setOnClickListener {
            txt_small_unit.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_small_unit.setTextColor(resources.getColor(R.color.colorWhite))
            txt_large_unit.setBackgroundResource(R.drawable.bg_white_blue_border)
            txt_large_unit.setTextColor(resources.getColor(R.color.colorBlueDark))

            IS_LARGE_UNIT = false
            txt_Unit_Diameter.text = resources.getString(R.string.Unit_MM)
            txt_layout_Price_per_1_kg.hint = resources.getString(R.string.Price_per_1_kg)
            txt_Unit_Weight.text = resources.getString(R.string.Unit_kg)

            KD = 1.0
            KDV = 1.0
        }

        txt_large_unit.setOnClickListener {
            txt_small_unit.setBackgroundResource(R.drawable.bg_white_blue_border)
            txt_small_unit.setTextColor(resources.getColor(R.color.colorBlueDark))
            txt_large_unit.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_large_unit.setTextColor(resources.getColor(R.color.colorWhite))

            IS_LARGE_UNIT = true
            txt_Unit_Diameter.text = resources.getString(R.string.Unit_Inches)
            txt_layout_Price_per_1_kg.hint = resources.getString(R.string.Price_per_1_lb)
            txt_Unit_Weight.text = resources.getString(R.string.Unit_lbs)

            KD = 25.4
            KDV = 0.453592
        }

        edt_Name.setOnClickListener { mDialogMaterial!!.show() }

        edt_Density.setOnClickListener { DialogDensity() }

        rg.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rb_length) {
                txt_layout_Length.visibility = View.VISIBLE
                linear_length_unit.visibility = View.VISIBLE
                txt_layout_Price_per_1_kg.visibility = View.VISIBLE
                txt_layout_Weight.visibility = View.GONE

                edt_Quantity.imeOptions = EditorInfo.IME_ACTION_NEXT
            } else if (i == R.id.rb_weight) {
                txt_layout_Length.visibility = View.GONE
                linear_length_unit.visibility = View.GONE
                txt_layout_Price_per_1_kg.visibility = View.GONE
                txt_layout_Weight.visibility = View.VISIBLE

                edt_Quantity.imeOptions = EditorInfo.IME_ACTION_DONE
            }
        }

        txt_Unit_MM.setOnClickListener {
            LENGTH_UNIT_VALUE = 0.001
            ResetLengthUnit()
            txt_Unit_MM.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_Unit_MM.setTextColor(resources.getColor(R.color.colorWhite))
        }

        txt_Unit_Meter.setOnClickListener {
            LENGTH_UNIT_VALUE = 1.0
            ResetLengthUnit()
            txt_Unit_Meter.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_Unit_Meter.setTextColor(resources.getColor(R.color.colorWhite))
        }

        txt_Unit_Inches.setOnClickListener {
            LENGTH_UNIT_VALUE = 0.0254
            ResetLengthUnit()
            txt_Unit_Inches.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_Unit_Inches.setTextColor(resources.getColor(R.color.colorWhite))
        }

        txt_Unit_Feet.setOnClickListener {
            LENGTH_UNIT_VALUE = 0.3048
            ResetLengthUnit()
            txt_Unit_Feet.setBackgroundResource(R.drawable.bg_blue_white_border)
            txt_Unit_Feet.setTextColor(resources.getColor(R.color.colorWhite))
        }

        edt_Diameter?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (edt_Diameter?.text!!.isNotEmpty()) {
                    txt_layout_Diameter?.error = null
                    txt_layout_Diameter?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        edt_Length?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (edt_Length?.text!!.isNotEmpty()) {
                    txt_layout_Length?.error = null
                    txt_layout_Length?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        edt_Weight?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (edt_Weight?.text!!.isNotEmpty()) {
                    txt_layout_Weight?.error = null
                    txt_layout_Weight?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun Calculation() {
        if (isValid()) {
            if (TextUtils.isEmpty(edt_Quantity.text.toString()))
                edt_Quantity.setText("1.0")
            val QUANTITY = edt_Quantity.text.toString().toDouble()
            val DENSITY = edt_Density.text.toString().toDouble()
            var L1 = ""
            var L2 = ""
            var L3 = ""
            var L4 = ""
            var V1 = ""
            var V2 = ""
            var V3 = ""
            var V4 = ""

            val decimalFormat = DecimalFormat("#.###")
            val decimalFormat2 = DecimalFormat("#.##")

            if (Selected_Shape_position == 0) {
                val CALCULATE_DIAMETER = (KD * edt_Diameter.text.toString().toDouble())

                if (rb_length.isChecked) {
                    if (TextUtils.isEmpty(edt_Price_per_1_kg.text.toString()))
                        edt_Price_per_1_kg.setText("0.0")
                    val PRICE = edt_Price_per_1_kg.text.toString().toDouble()

                    val CALCULATE_LENGTH =
                        (LENGTH_UNIT_VALUE * edt_Length.text.toString().toDouble())

                    WEIGHT_MAIN =
                        ((0.001 * (DENSITY * (CALCULATE_LENGTH * (CALCULATE_DIAMETER * (CALCULATE_DIAMETER * 3.14))))) / 4.0)

                    if (IS_LARGE_UNIT) {
                        WEIGHT_MAIN *= 2.20462
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY
                        WEIGHT_PRICE = WEIGHT_MAIN * PRICE
                        WEIGHT_QUANTITY_PRICE = WEIGHT_QUANTITY * PRICE

                        L1 = "Weight : "
                        L2 = "Cost : "
                        L3 = "Weight $QUANTITY pcs : "
                        L4 = "Cost of $QUANTITY pcs : "

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} lbs"
                        V2 = "${decimalFormat2.format(WEIGHT_PRICE)} "
                        V3 = "${decimalFormat.format(WEIGHT_QUANTITY)} lbs"
                        V4 = "${decimalFormat2.format(WEIGHT_QUANTITY_PRICE)} "
                    } else {
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY
                        WEIGHT_PRICE = WEIGHT_MAIN * PRICE
                        WEIGHT_QUANTITY_PRICE = WEIGHT_QUANTITY * PRICE

                        L1 = "Weight : "
                        L2 = "Cost : "
                        L3 = "Weight $QUANTITY pcs : "
                        L4 = "Cost of $QUANTITY pcs : "

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} kg"
                        V2 = "${decimalFormat2.format(WEIGHT_PRICE)} "
                        V3 = "${decimalFormat.format(WEIGHT_QUANTITY)} kg"
                        V4 = "${decimalFormat2.format(WEIGHT_QUANTITY_PRICE)} "
                    }
                } else if (rb_weight.isChecked) {
                    val CALCULATE_WEIGHT = KDV * edt_Weight.text.toString().toDouble()

                    WEIGHT_MAIN =
                        ((CALCULATE_WEIGHT * 4.0) / ((((3.14 * CALCULATE_DIAMETER) * CALCULATE_DIAMETER) * DENSITY) * 0.001))

                    if (IS_LARGE_UNIT) {
                        WEIGHT_MAIN *= 3.28084
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY

                        L1 = "Length : "
                        L2 = "Length $QUANTITY pcs : "
                        L3 = ""
                        L4 = ""

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} ft"
                        V2 = "${decimalFormat.format(WEIGHT_QUANTITY)} ft"
                        V3 = ""
                        V4 = ""
                    } else {
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY

                        L1 = "Length : "
                        L2 = "Length $QUANTITY pcs : "
                        L3 = ""
                        L4 = ""

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} m"
                        V2 = "${decimalFormat.format(WEIGHT_QUANTITY)} m"
                        V3 = ""
                        V4 = ""
                    }
                }
            } else if (Selected_Shape_position == 3) {
                val CALCULATE_NUMBER = (KD * edt_Diameter.text.toString().toDouble())
                val NUMBER = CALCULATE_NUMBER / 2
                if (rb_length.isChecked) {
                    if (TextUtils.isEmpty(edt_Price_per_1_kg.text.toString()))
                        edt_Price_per_1_kg.setText("0.0")
                    val PRICE = edt_Price_per_1_kg.text.toString().toDouble()
                    val CALCULATE_LENGTH =
                        (LENGTH_UNIT_VALUE * edt_Length.text.toString().toDouble())


                    WEIGHT_MAIN = NUMBER * 3.464 * NUMBER * CALCULATE_LENGTH * DENSITY * 0.001

                    if (IS_LARGE_UNIT) {
                        WEIGHT_MAIN *= 2.20462
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY
                        WEIGHT_PRICE = WEIGHT_MAIN * PRICE          //d7
                        WEIGHT_QUANTITY_PRICE = WEIGHT_QUANTITY * PRICE     //d9

                        L1 = "Weight : "
                        L2 = "Cost : "
                        L3 = "Weight $QUANTITY pcs : "
                        L4 = "Cost of $QUANTITY pcs : "

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} lbs"
                        V2 = "${decimalFormat2.format(WEIGHT_PRICE)} "
                        V3 = "${decimalFormat.format(WEIGHT_QUANTITY)} lbs"
                        V4 = "${decimalFormat2.format(WEIGHT_QUANTITY_PRICE)} "
                    } else {
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY
                        WEIGHT_PRICE = WEIGHT_MAIN * PRICE
                        WEIGHT_QUANTITY_PRICE = WEIGHT_QUANTITY * PRICE

                        L1 = "Weight : "
                        L2 = "Cost : "
                        L3 = "Weight $QUANTITY pcs : "
                        L4 = "Cost of $QUANTITY pcs : "

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} kg"
                        V2 = "${decimalFormat2.format(WEIGHT_PRICE)} "
                        V3 = "${decimalFormat.format(WEIGHT_QUANTITY)} kg"
                        V4 = "${decimalFormat2.format(WEIGHT_QUANTITY_PRICE)} "
                    }
                } else if (rb_weight.isChecked) {
                    val CALCULATE_WEIGHT = KDV * edt_Weight.text.toString().toDouble()

                    WEIGHT_MAIN =
                        (CALCULATE_WEIGHT / ((((3.464 * NUMBER) * NUMBER) * DENSITY) * 0.001))

                    if (IS_LARGE_UNIT) {
                        WEIGHT_MAIN *= 3.28084
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY

                        L1 = "Length : "
                        L2 = "Length $QUANTITY pcs : "
                        L3 = ""
                        L4 = ""

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} ft"
                        V2 = "${decimalFormat.format(WEIGHT_QUANTITY)} ft"
                        V3 = ""
                        V4 = ""
                    } else {
                        WEIGHT_QUANTITY = WEIGHT_MAIN * QUANTITY

                        L1 = "Length : "
                        L2 = "Length $QUANTITY pcs : "
                        L3 = ""
                        L4 = ""

                        V1 = "${decimalFormat.format(WEIGHT_MAIN)} m"
                        V2 = "${decimalFormat.format(WEIGHT_QUANTITY)} m"
                        V3 = ""
                        V4 = ""
                    }
                }
            }
            CalculationDialog(L1, L2, L3, L4, V1, V2, V3, V4).show(supportFragmentManager, "")
        }
    }

    private fun setLayoutHeight() {
        var relative_height: Int
        var linear_height: Int
        val vto = relative_img.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                relative_img.viewTreeObserver.removeOnGlobalLayoutListener(this)
                relative_height = relative_img.measuredHeight

                val vto_linear_units = linear_units.viewTreeObserver
                vto_linear_units.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        linear_units.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        linear_height = linear_units.measuredHeight

                        val params =
                            linear_units.layoutParams as RelativeLayout.LayoutParams
                        params.setMargins(0, (relative_height - (linear_height / 2)), 0, 0)
                    }
                })
            }
        })
    }

    private fun ResetLengthUnit() {
        txt_Unit_MM.setBackgroundResource(R.drawable.bg_white_blue_border)
        txt_Unit_Meter.setBackgroundResource(R.drawable.bg_white_blue_border)
        txt_Unit_Inches.setBackgroundResource(R.drawable.bg_white_blue_border)
        txt_Unit_Feet.setBackgroundResource(R.drawable.bg_white_blue_border)

        txt_Unit_MM.setTextColor(resources.getColor(R.color.colorBlueDark))
        txt_Unit_Meter.setTextColor(resources.getColor(R.color.colorBlueDark))
        txt_Unit_Inches.setTextColor(resources.getColor(R.color.colorBlueDark))
        txt_Unit_Feet.setTextColor(resources.getColor(R.color.colorBlueDark))
    }

    private fun isValid(): Boolean {
        if (rb_length.isChecked) {
            txt_layout_Diameter?.error = null
            txt_layout_Length?.error = null

            txt_layout_Diameter.isErrorEnabled = false
            txt_layout_Length.isErrorEnabled = false

            return when {
                edt_Diameter?.text?.isEmpty()!! -> {
                    txt_layout_Diameter?.error = resources.getString(R.string.err_msg_Diameter)
                    false
                }
                edt_Diameter.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                    txt_layout_Diameter?.error =
                        resources.getString(R.string.err_msg_valid_Diameter)
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
        txt_layout_Diameter?.error = null
        txt_layout_Weight?.error = null
        txt_layout_Quantity?.error = null

        txt_layout_Diameter.isErrorEnabled = false
        txt_layout_Weight.isErrorEnabled = false
        txt_layout_Quantity.isErrorEnabled = false

        return when {
            edt_Diameter?.text?.isEmpty()!! -> {
                txt_layout_Diameter?.error = resources.getString(R.string.err_msg_Diameter)
                false
            }
            edt_Diameter.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                txt_layout_Diameter?.error = resources.getString(R.string.err_msg_valid_Diameter)
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

    @SuppressLint("InflateParams", "RestrictedApi")
    private fun DialogMaterial() {
        val layout: View = LayoutInflater.from(ac).inflate(R.layout.dialog_material, null)
        val alert = ac?.let { AlertDialog.Builder(it) }
        alert?.setCancelable(true)
        alert?.setView(layout)
        mDialogMaterial = alert?.create()
        mDialogMaterial?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        mDialogMaterial!!.window!!.attributes.windowAnimations = R.style.DialogAnimation

        val txt_label = layout.findViewById<TextView>(R.id.txt_label)
        txt_label.visibility = View.VISIBLE
        val view = layout.findViewById<View>(R.id.view)
        view.visibility = View.VISIBLE
        val fab_add = layout.findViewById<FloatingActionButton>(R.id.fab_add)
        fab_add.visibility = View.VISIBLE

        fab_add.setOnClickListener { DialogAddMaterial() }

        val mRecyclerView = layout.findViewById<RecyclerView>(R.id.mRecyclerView)
        adp_material = MaterialAdapter()
        mRecyclerView.adapter = adp_material
    }

    internal inner class MaterialAdapter : RecyclerView.Adapter<MaterialAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(ac).inflate(R.layout.graphics_material, parent, false)
            return MyViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.SetData(position)
        }

        override fun getItemCount(): Int {
            return materialList.size
        }

        internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var txt: TextView = itemView.findViewById(R.id.txt)
            private var linear_layout: LinearLayout = itemView.findViewById(R.id.linear_layout)

            fun SetData(pos: Int) {
                val m: Model_Material = materialList[pos]
                txt.text = m.Name

                if (pos % 2 == 0) {
                    linear_layout.setBackgroundColor(resources.getColor(R.color.colorWhite))
                } else {
                    linear_layout.setBackgroundColor(resources.getColor(R.color.colorLightGray))
                }

                itemView.setOnClickListener {
                    SELECTED_MATERIAL = m
                    edt_Name.setText(SELECTED_MATERIAL?.Name)
                    edt_Density.setText(SELECTED_MATERIAL?.D_1)
                    Config.SetMaterial(ac as FirstFourActivity, m)
                    if (mDialogMaterial?.isShowing!!)
                        mDialogMaterial?.dismiss()
                }

                itemView.setOnLongClickListener {
                    DialogDelete(m.Name)
                    true
                }
            }
        }
    }

    fun DialogDelete(material_name: String) {
        val mDialog: AlertDialog?
        mDialog = AlertDialog.Builder(ac as FirstFourActivity).create()
        mDialog.setTitle("Warning !")
        mDialog.setMessage("Are you sure you want to delete $material_name ?")
        mDialog.setCancelable(true)

        mDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "Delete",
            DialogInterface.OnClickListener { dialog, which ->
                db?.Delete_Material(material_name)
                Toast.makeText(ac, "$material_name deleted successfully", Toast.LENGTH_SHORT).show()

                materialList.clear()
                db?.getMaterialList()?.let { materialList.addAll(it) }
                adp_material?.notifyDataSetChanged()

                mDialog.dismiss()
            })

        mDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                mDialog.dismiss()
            })

        mDialog.show()
        mDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            ?.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        mDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            ?.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    @SuppressLint("InflateParams")
    private fun DialogAddMaterial() {
        val layout: View = LayoutInflater.from(ac).inflate(R.layout.dialog_material_add, null)
        val alert = ac?.let { AlertDialog.Builder(it) }
        alert?.setCancelable(true)
        alert?.setView(layout)
        mDialogAddMaterial = alert?.create()
        mDialogAddMaterial?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        mDialogAddMaterial!!.window!!.attributes.windowAnimations = R.style.DialogAnimation

        add_txt_layout_Name = layout.findViewById(R.id.txt_layout_Name)
        add_txt_layout_D1 = layout.findViewById(R.id.txt_layout_D1)
        add_txt_layout_D2 = layout.findViewById(R.id.txt_layout_D2)
        add_txt_layout_D3 = layout.findViewById(R.id.txt_layout_D3)
        add_txt_layout_D4 = layout.findViewById(R.id.txt_layout_D4)
        add_txt_layout_D5 = layout.findViewById(R.id.txt_layout_D5)

        add_edt_Name = layout.findViewById(R.id.edt_Name)
        add_edt_D1 = layout.findViewById(R.id.edt_D1)
        add_edt_D2 = layout.findViewById(R.id.edt_D2)
        add_edt_D3 = layout.findViewById(R.id.edt_D3)
        add_edt_D4 = layout.findViewById(R.id.edt_D4)
        add_edt_D5 = layout.findViewById(R.id.edt_D5)
        val btn_add = layout.findViewById<Button>(R.id.btn_add)

        CONTROL_LISTENER_ADD_DIALOG(btn_add)

        mDialogAddMaterial!!.show()
    }

    private fun CONTROL_LISTENER_ADD_DIALOG(
        btn_add: Button
    ) {
        btn_add.setOnClickListener {
            if (isValidMaterial()) {
                db?.InsertMaterial(
                    add_edt_Name?.text.toString(),
                    add_edt_D1?.text.toString(),
                    add_edt_D2?.text.toString(),
                    add_edt_D3?.text.toString(),
                    add_edt_D4?.text.toString(),
                    add_edt_D5?.text.toString()
                )

                materialList.clear()
                db?.getMaterialList()?.let { materialList.addAll(it) }

                Log.e("LLL_Size : ", "" + materialList.size)

                adp_material?.notifyDataSetChanged()

                mDialogAddMaterial?.dismiss()
            }
        }

        add_edt_Name?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_Name?.text!!.isNotEmpty()) {
                    add_txt_layout_Name?.error = null
                    add_txt_layout_Name?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        add_edt_D1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_D1?.text!!.isNotEmpty()) {
                    add_txt_layout_D1?.error = null
                    add_txt_layout_D1?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        add_edt_D2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_D2?.text!!.isNotEmpty()) {
                    add_txt_layout_D2?.error = null
                    add_txt_layout_D2?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        add_edt_D3?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_D3?.text!!.isNotEmpty()) {
                    add_txt_layout_D3?.error = null
                    add_txt_layout_D3?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        add_edt_D4?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_D4?.text!!.isNotEmpty()) {
                    add_txt_layout_D4?.error = null
                    add_txt_layout_D4?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        add_edt_D5?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (add_edt_D5?.text!!.isNotEmpty()) {
                    add_txt_layout_D5?.error = null
                    add_txt_layout_D5?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun isValidMaterial(): Boolean {
        add_txt_layout_Name?.error = null
        add_txt_layout_D1?.error = null
        add_txt_layout_D2?.error = null
        add_txt_layout_D3?.error = null
        add_txt_layout_D4?.error = null
        add_txt_layout_D5?.error = null

        add_txt_layout_Name?.isErrorEnabled = false
        add_txt_layout_D1?.isErrorEnabled = false
        add_txt_layout_D2?.isErrorEnabled = false
        add_txt_layout_D3?.isErrorEnabled = false
        add_txt_layout_D4?.isErrorEnabled = false
        add_txt_layout_D5?.isErrorEnabled = false
        when {
            add_edt_Name?.text?.isEmpty()!! -> {
                add_txt_layout_Name?.error = "Please Enter Material Name"
                return false
            }
            db?.Check_Collection_Exist(add_edt_Name?.text.toString())!! -> {
                add_txt_layout_Name?.error = "This Material is already exist."
                return false
            }
            add_edt_D1?.text?.isEmpty()!! -> {
                add_txt_layout_D1?.error = "Please Enter Standard Density 1"
                return false
            }
            add_edt_D1?.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                add_txt_layout_D1?.error = "Please Enter valid Density"
                return false
            }
            add_edt_D2?.text?.isEmpty()!! -> {
                add_txt_layout_D2?.error = "Please Enter Standard Density 2"
                return false
            }
            add_edt_D2?.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                add_txt_layout_D2?.error = "Please Enter valid Density"
                return false
            }
            add_edt_D3?.text?.isEmpty()!! -> {
                add_txt_layout_D3?.error = "Please Enter Standard Density 3"
                return false
            }
            add_edt_D3?.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                add_txt_layout_D3?.error = "Please Enter valid Density"
                return false
            }
            add_edt_D4?.text?.isEmpty()!! -> {
                add_txt_layout_D4?.error = "Please Enter Standard Density 4"
                return false
            }
            add_edt_D4?.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                add_txt_layout_D4?.error = "Please Enter valid Density"
                return false
            }
            add_edt_D5?.text?.isEmpty()!! -> {
                add_txt_layout_D5?.error = "Please Enter Standard Density 5"
                return false
            }
            add_edt_D5?.text?.toString()?.toDoubleOrNull() == 0.0 -> {
                add_txt_layout_D5?.error = "Please Enter valid Density"
                return false
            }
            else -> return true
        }
    }

    @SuppressLint("InflateParams")
    private fun DialogDensity() {
        val layout: View = LayoutInflater.from(ac).inflate(R.layout.dialog_density, null)
        val alert = ac?.let { AlertDialog.Builder(it) }
        alert?.setCancelable(true)
        alert?.setView(layout)
        mDialogDensity = alert?.create()
        mDialogDensity?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        mDialogDensity!!.window!!.attributes.windowAnimations = R.style.DialogAnimation

        val edt_Custom_Density = layout.findViewById<EditText>(R.id.edt_Custom_Density)
        val txt_1 = layout.findViewById<TextView>(R.id.txt_1)
        val txt_2 = layout.findViewById<TextView>(R.id.txt_2)
        val txt_3 = layout.findViewById<TextView>(R.id.txt_3)
        val txt_4 = layout.findViewById<TextView>(R.id.txt_4)
        val txt_5 = layout.findViewById<TextView>(R.id.txt_5)

        txt_1.text = SELECTED_MATERIAL?.D_1
        txt_2.text = SELECTED_MATERIAL?.D_2
        txt_3.text = SELECTED_MATERIAL?.D_3
        txt_4.text = SELECTED_MATERIAL?.D_4
        txt_5.text = SELECTED_MATERIAL?.D_5

        txt_1.setOnClickListener {
            edt_Density.setText(txt_1.text)
            mDialogDensity?.dismiss()
        }
        txt_2.setOnClickListener {
            edt_Density.setText(txt_2.text)
            mDialogDensity?.dismiss()
        }
        txt_3.setOnClickListener {
            edt_Density.setText(txt_3.text)
            mDialogDensity?.dismiss()
        }
        txt_4.setOnClickListener {
            edt_Density.setText(txt_4.text)
            mDialogDensity?.dismiss()
        }
        txt_5.setOnClickListener {
            edt_Density.setText(txt_5.text)
            mDialogDensity?.dismiss()
        }

        edt_Custom_Density.setOnEditorActionListener(OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (edt_Custom_Density.text?.isNotEmpty()!!) {
                    edt_Density.setText(edt_Custom_Density.text.toString())
                }
                mDialogDensity?.dismiss()
                return@OnEditorActionListener true
            }
            false
        })
        mDialogDensity!!.show()
    }
}
