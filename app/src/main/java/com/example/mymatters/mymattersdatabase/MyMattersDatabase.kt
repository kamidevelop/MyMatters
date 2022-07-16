package com.example.mymatters.mymattersdatabase
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

data class Person(val personName : String, val photoUri : String, val wallet : Double, val coinbox : Double)

class MyMattersDatabase{
    companion object {
        fun writeInFamilyData(person: Person, context: Context) {

        }
    }
}