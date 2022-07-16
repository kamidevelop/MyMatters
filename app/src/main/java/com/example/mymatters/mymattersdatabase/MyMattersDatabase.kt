package com.example.mymatters.mymattersdatabase
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import kotlin.collections.*
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

        fun readFamilyData(context: Context): List<Person> {
            val listOfPersons: MutableList<Person> = mutableListOf()
            val database : SQLiteDatabase = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if (database.isOpen) {
                val curs = database.rawQuery("SELECT * FROM Family;", null)
                if(curs.moveToFirst()) {
                    listOfPersons.add(
                        Person(
                            curs.getString(1),
                            curs.getString(2),
                            curs.getDouble(3),
                            curs.getDouble(4),
                        )
                    )
                }
                while (curs.moveToNext()) {
                    listOfPersons.add(
                        Person(
                            curs.getString(1),
                            curs.getString(2),
                            curs.getDouble(3),
                            curs.getDouble(4),
                        )
                    )
                }
                curs.close()
                database.close()
            }
            return listOfPersons.toList()
        }

        fun readFamilyPersonData(context: Context, personId: Int) : Person?{
            val database : SQLiteDatabase = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen) {
                val curs = database.rawQuery("SELECT * FROM Family WHERE id = $personId;",null)
                if(curs.moveToFirst()){
                    val person = Person(curs.getString(1),curs.getString(2),curs.getDouble(3), curs.getDouble(4))
                    curs.close()
                    database.close()
                    return person
                }
                else database.close()
            }
            return null
        }

        fun updateFamilyData(context: Context, personId : Int, person: Person){
            val database : SQLiteDatabase = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("name", person.personName)
                contentValues.put("photoUri",person.photoUri)
                contentValues.put("wallet",person.wallet)
                contentValues.put("coinbox",person.coinbox)
                database.update("Family", contentValues,"id = ?", arrayOf(personId.toString()))
                database.close()
            }
        }

        fun deleteFamilyData(context: Context, personId : Int) : Int{
            if(personId == -1) return -1
            var database : SQLiteDatabase = context.openOrCreateDatabase(DATA_BASE_NAME, Context.MODE_PRIVATE, null)
            if(database.isOpen){
                database.delete("Family","id = ?", arrayOf(personId.toString()))
                database.close()
            }
            return personId
        }
    }
}