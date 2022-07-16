package com.example.mymatters.mymattersdatabase
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

data class Person(val personName : String, val photoUri : String, val wallet : Double, val coinbox : Double)

class MyMattersDatabase{
    companion object {
        const val DATA_BASE_NAME = "mymattersfinance.db"
        fun writeInFamilyData(person: Person, context: Context) {
            val database : SQLiteDatabase = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("name", person.personName)
                contentValues.put("photoUri",person.photoUri)
                contentValues.put("wallet",person.wallet)
                contentValues.put("coinbox",person.coinbox)
                contentValues.put("wallet",person.wallet)
                database.insert("Family",null,contentValues)
                database.close()
            }
        }
    }
}