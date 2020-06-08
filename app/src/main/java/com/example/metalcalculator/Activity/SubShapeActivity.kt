package com.example.metalcalculator.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.metalcalculator.Custom.Config
import com.example.metalcalculator.Custom.SpacesItemDecoration
import com.example.metalcalculator.R
import kotlinx.android.synthetic.main.activity_shapes.*

class SubShapeActivity : AppCompatActivity() {

    private var ac: Activity? = null
    private var Selected_Shape_position = 1

    private var shapes_list = intArrayOf(
        R.drawable.vec_shape_2_1,
        R.drawable.vec_shape_2_2
    )
    private var shapes_names_list = arrayOf("Arbitrary size", "GOST 3262-75")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shapes)

        INITIALIZE()
    }

    private fun INITIALIZE() {
        ac = this@SubShapeActivity
        Selected_Shape_position = intent.getIntExtra("Selected_Shape_position", 1)
        getShapeList()

        mRecyclerView.layoutManager = GridLayoutManager(ac, 2)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        mRecyclerView.adapter = ShapesAdapter()
    }

    private fun getShapeList() {
        when (Selected_Shape_position) {
            1 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_2_1,
                    R.drawable.vec_shape_2_2
                )
                shapes_names_list = arrayOf("Arbitrary size", "GOST 3262-75")
            }
            2 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_3_1,
                    R.drawable.vec_shape_3_2
                )
                shapes_names_list = arrayOf("", "")
            }
            4 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_5_1,
                    R.drawable.vec_shape_5_2,
                    R.drawable.vec_shape_5_3,
                    R.drawable.vec_shape_5_4,
                    R.drawable.vec_shape_5_5
                )
                shapes_names_list = arrayOf(
                    "Arbitrary size",
                    "GOST 8706-78",
                    "GOST 8568-77",
                    "GOST 8568-77",
                    "GOST 24045-94"
                )
            }
            6 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_7_1,
                    R.drawable.vec_shape_7_2,
                    R.drawable.vec_shape_7_1,
                    R.drawable.vec_shape_7_2,
                    R.drawable.vec_shape_7_1,
                    R.drawable.vec_shape_7_2,
                    R.drawable.vec_shape_7_1,
                    R.drawable.vec_shape_7_2,
                    R.drawable.vec_shape_7_1,
                    R.drawable.vec_shape_7_2,
                    R.drawable.vec_shape_7_1
                )
                shapes_names_list = arrayOf(
                    "Arbitrary size",
                    "GOST 8639-82",
                    "GOST 8645-68",
                    "GOST 30245-2003",
                    "GOST 30245-2003",
                    "IS 4923",
                    "IS 4923",
                    "EN10219",
                    "EN10219",
                    "ASTM A1085",
                    "ASTM A1085"
                )
            }
            7 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_8_1,
                    R.drawable.vec_shape_8_2,
                    R.drawable.vec_shape_8_1,
                    R.drawable.vec_shape_8_2,
                    R.drawable.vec_shape_8_1,
                    R.drawable.vec_shape_8_2,
                    R.drawable.vec_shape_8_1
                )
                shapes_names_list = arrayOf(
                    "Arbitrary size",
                    "GOST 8509-93",
                    "GOST 8510-86",
                    "IS 808 : 1989",
                    "IS 808 : 1989",
                    "EN 100546-1 : 1998",
                    "EN 100546-1 : 1998"
                )
            }
            8 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_3,
                    R.drawable.vec_shape_9_3,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_1,
                    R.drawable.vec_shape_9_2,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_4,
                    R.drawable.vec_shape_9_2
                )
                shapes_names_list = arrayOf(
                    "Arbitrary size",
                    "GOST 26020-83",
                    "GOST 8239-89",
                    "IPN DIN 1025-1",
                    "IPE Euronorm 19-57",
                    "HEM Euronorm 53-62",
                    "HEB Euronorm 53-62",
                    "HEA Euronorm 53-62",
                    "IS 808 : 1989",
                    "СТО АСЧМ 20-93",
                    "S-American standard",
                    "HP-American standard",
                    "HD ASTM A 6/A 6M",
                    "HL ASTM A 6/A 6M",
                    "HP piles",
                    "IFB EN 10163-3:2004",
                    "SFB EN 10163-3:2004",
                    "ГОСТ 19425-74",
                    "W-ASTM A 6/A 6M-07",
                    "UB-BS 4-1 : 2005",
                    "J-BS 4-1 : 2005",
                    "UC-BS 4-1 : 2005",
                    "UBP-BS 4-1 : 2005",
                    "H-JIS G 3192:2005"
                )
            }
            9 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_10_1,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_1,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_1,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_2,
                    R.drawable.vec_shape_10_1,
                    R.drawable.vec_shape_10_2
                )
                shapes_names_list = arrayOf(
                    "Arbitrary size",
                    "GOST 8240-89",
                    "UPE EN 10279 : 2000",
                    "UPN DIN 1026-1",
                    "IS 808 : 1989",
                    "IS 808 : 1989",
                    "C-American standard",
                    "U=AM standard",
                    "MC-ASTM A 6/A 6M-07",
                    "PFC-BS 4-1:2005",
                    "CH-BS 4-1 : 1993"
                )
            }
            11 -> {
                shapes_list = intArrayOf(
                    R.drawable.vec_shape_12,
                    R.drawable.vec_shape_12_2
                )
                shapes_names_list = arrayOf(
                    "Triangular Bar",
                    "Triangular Pipe"
                )
            }
        }
    }

    internal inner class ShapesAdapter : RecyclerView.Adapter<ShapesAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(ac).inflate(R.layout.graphics_shapes, parent, false)
            return MyViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.SetData(position)
        }

        override fun getItemCount(): Int {
            return shapes_list.size
        }

        internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var img_thumb: ImageView = itemView.findViewById(R.id.img_thumb)
            private var txt_shape_name: TextView = itemView.findViewById(R.id.txt_shape_name)

            fun SetData(pos: Int) {
                img_thumb.setImageResource(shapes_list[pos])
                txt_shape_name.text = shapes_names_list[pos]

                itemView.setOnClickListener {
                    Config.SetFireBaseAnalytics(
                        ac as SubShapeActivity,
                        "sub_shape_" + shapes_names_list[pos]
                    )
                    if (Selected_Shape_position == 1) {
                        if (pos == 0) {
                            val mIntent = Intent(ac, SecondActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else if (pos == 1) {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        }
                    } else if (Selected_Shape_position == 1 || Selected_Shape_position == 2 || Selected_Shape_position == 11) {
                        val mIntent = Intent(ac, SecondActivity::class.java)
                        mIntent.putExtra(
                            "Selected_Sub_Shape_position",
                            "${Selected_Shape_position}_$pos"
                        )
                        ac?.startActivity(mIntent)
                    } else if (Selected_Shape_position == 4) {
                        if (pos == 4) {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else {
                            val mIntent = Intent(ac, FifthActivity::class.java)
                            mIntent.putExtra("Selected_Sub_Shape_position", pos)
                            ac?.startActivity(mIntent)
                        }
                    } else if (Selected_Shape_position == 6) {
                        if (pos == 0) {
                            val mIntent = Intent(ac, OtherFirstActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        }
                    } else if (Selected_Shape_position == 7) {
                        if (pos == 0) {
                            val mIntent = Intent(ac, OtherFirstActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        }
                    } else if (Selected_Shape_position == 8) {
                        if (pos == 0) {
                            val mIntent = Intent(ac, OtherFirstActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        }
                    } else if (Selected_Shape_position == 9) {
                        if (pos == 0) {
                            val mIntent = Intent(ac, OtherFirstActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        } else {
                            val mIntent = Intent(ac, SixElevenActivity::class.java)
                            mIntent.putExtra(
                                "Selected_Sub_Shape_position",
                                "${Selected_Shape_position}_$pos"
                            )
                            ac?.startActivity(mIntent)
                        }
                    }
                }
            }
        }
    }
}
