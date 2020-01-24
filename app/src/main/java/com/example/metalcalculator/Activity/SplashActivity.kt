package com.example.metalcalculator.Activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.metalcalculator.Custom.Config
import com.example.metalcalculator.Database.DataBaseHandler
import com.example.metalcalculator.Model.Model_Material
import com.example.metalcalculator.R


class SplashActivity : AppCompatActivity() {

    private var ac: Activity? = null
    private var db: DataBaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        else
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        INITIALIZE()
    }

    private fun INITIALIZE() {
        ac = this@SplashActivity
        db = DataBaseHandler(ac as SplashActivity)

        if (db!!.Is_Empty()) {
            val names: Array<out String> = Config.getMaterialNameList(ac as SplashActivity)
            for (i in names.indices) {
                db?.InsertMaterial(
                    names[i],
                    Config.d1[i].toString(),
                    Config.d2[i].toString(),
                    Config.d3[i].toString(),
                    Config.d4[i].toString(),
                    Config.d5[i].toString()
                )

                if (names[i] == "Aluminum") {
                    Config.SetMaterial(
                        ac as SplashActivity, Model_Material(
                            names[i],
                            Config.d1[i].toString(),
                            Config.d2[i].toString(),
                            Config.d3[i].toString(),
                            Config.d4[i].toString(),
                            Config.d5[i].toString()
                        )
                    )
                }

            }
        }

        Handler().postDelayed({
            startActivity(Intent(ac, ShapesActivity::class.java))
            finish()
        }, 2000)
    }
}
