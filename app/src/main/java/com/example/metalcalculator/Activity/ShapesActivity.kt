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
import androidx.recyclerview.widget.RecyclerView
import com.example.metalcalculator.Custom.Config
import com.example.metalcalculator.Custom.SpacesItemDecoration
import com.example.metalcalculator.R
import kotlinx.android.synthetic.main.activity_shapes.*


class ShapesActivity : AppCompatActivity() {

    private var ac: Activity? = null
    private var back_pressed: Long = 0

    private val shapes_list = intArrayOf(
        R.drawable.vec_shape_1,
        R.drawable.vec_shape_2,
        R.drawable.vec_shape_3,
        R.drawable.vec_shape_4,
        R.drawable.vec_shape_5,
        R.drawable.vec_shape_6,
        R.drawable.vec_shape_7,
        R.drawable.vec_shape_8,
        R.drawable.vec_shape_9,
        R.drawable.vec_shape_10,
        R.drawable.vec_shape_11,
        R.drawable.vec_shape_12,
        R.drawable.vec_shape_13
    )
    private val shapes_name_list = arrayOf(
        "Round bar",
        "Pipe / tube",
        "Square / sheet",
        "Hexagonal Bar",
        "Plate / sheet",
        "Profile bar",
        "Rectangular pipe",
        "Angle",
        "I-BEAM / H-BEAM",
        "C-Channel",
        "Spiral pipe",
        "Triangular BEAM",
        "T Bar"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shapes)

        INITIALIZE()
    }

    private fun INITIALIZE() {
        ac = this@ShapesActivity

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        mRecyclerView.adapter = ShapesAdapter()
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
                txt_shape_name.text = shapes_name_list[pos]

                itemView.setOnClickListener {
                    Config.SetFireBaseAnalytics(ac as ShapesActivity, shapes_name_list[pos])
                    if (pos == 0 || pos == 3) {
                        val mIntent = Intent(ac, FirstFourActivity::class.java)
                        mIntent.putExtra("Selected_Shape_position", pos)
                        ac?.startActivity(mIntent)
                    } else if (pos == 1 || pos == 2 || pos == 4 || pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 11) {
                        val mIntent = Intent(ac, SubShapeActivity::class.java)
                        mIntent.putExtra("Selected_Shape_position", pos)
                        ac?.startActivity(mIntent)
                    } else if (pos == 5 || pos == 10) {
                        val mIntent = Intent(ac, SixElevenActivity::class.java)
                        mIntent.putExtra("Selected_Shape_position", pos)
                        ac?.startActivity(mIntent)
                    } else if (pos == 12) {
                        val mIntent = Intent(ac, OtherFirstActivity::class.java)
                        mIntent.putExtra("Selected_Sub_Shape_position", "${pos}_0")
                        ac?.startActivity(mIntent)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish()
            moveTaskToBack(true)
        } else {
            Config.ShowSnackBar(ac as ShapesActivity, "Press once again to exit", linear_main)
                .show()
            back_pressed = System.currentTimeMillis()
        }
    }
}
