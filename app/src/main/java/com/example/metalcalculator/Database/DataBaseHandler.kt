package com.example.metalcalculator.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.metalcalculator.Model.Model_Material


class DataBaseHandler(mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Data_DB.db"

        // table name
        private const val TABLE_MATERIAL = "Material_Details"

        // Table Columns names
        private const val MATERIAL_NAME = "Material"
        private const val D_1 = "D_1"
        private const val D_2 = "D_2"
        private const val D_3 = "D_3"
        private const val D_4 = "D_4"
        private const val D_5 = "D_5"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_DATABASE_MATERIAL = "CREATE TABLE " + TABLE_MATERIAL + "(" +
                MATERIAL_NAME + " TEXT," +
                D_1 + " TEXT," +
                D_2 + " TEXT," +
                D_3 + " TEXT," +
                D_4 + " TEXT," +
                D_5 + " TEXT);"
        db.execSQL(CREATE_DATABASE_MATERIAL)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MATERIAL")
        onCreate(db)
    }

    fun Is_Empty(): Boolean {
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_MATERIAL", null)
        if (cursor.count <= 0) {
            cursor.close()
            return true
        }
        cursor.close()
        return false
    }

    fun Delete_Material(material_name: String) {
        val db = this.writableDatabase
        db.delete(TABLE_MATERIAL, "$MATERIAL_NAME = ?", arrayOf(material_name))
    }

    @SuppressLint("DefaultLocale")
    fun InsertMaterial(
        material_name: String,
        d1: String,
        d2: String,
        d3: String,
        d4: String,
        d5: String
    ) {
        val UpperString = material_name.substring(0, 1).toUpperCase() + material_name.substring(1)
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(MATERIAL_NAME, UpperString)
        values.put(D_1, d1)
        values.put(D_2, d2)
        values.put(D_3, d3)
        values.put(D_4, d4)
        values.put(D_5, d5)
        db.insert(TABLE_MATERIAL, null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun getMaterialList(): ArrayList<Model_Material> {
        val arrayList = ArrayList<Model_Material>()
        val db = this.writableDatabase
        val Query = "SELECT * FROM $TABLE_MATERIAL ORDER BY UPPER($MATERIAL_NAME) ASC"
        val cur = db.rawQuery(Query, null)
        if (cur.count > 0) {
            cur.moveToFirst()
            do {
                val m = Model_Material(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3),
                    cur.getString(4),
                    cur.getString(5)
                )
                arrayList.add(m)
            } while (cur.moveToNext())
        }
        cur.close()
        return arrayList
    }

    @SuppressLint("DefaultLocale")
    fun Check_Collection_Exist(material: String): Boolean {
        val db = this.writableDatabase
        val Query = ("SELECT * FROM '" + TABLE_MATERIAL
                + "' WHERE UPPER(" + MATERIAL_NAME + ") = '" + material.toUpperCase() + "'")
        val cursor = db.rawQuery(Query, null)
        if (cursor.count <= 0) {
            cursor.close()
            db.close()
            return false
        }
        cursor.close()
        db.close()
        return true
    }

}
