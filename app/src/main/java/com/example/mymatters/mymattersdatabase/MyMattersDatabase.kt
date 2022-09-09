package com.example.mymatters.mymattersdatabase
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import kotlin.collections.*
data class Person(val id : Int, val personName : String, val photoUri : String, val wallet : Double, val coinbox : Double)
data class Income(val id : Int, val date : String, val who : Int, val sum : Double, val where : String, val comment : String)
data class Demand(val id : Int, val date : String, val who : Int, val sum : Double, val from : String, val category : String)
data class Target(val id : Int, val photoUri: String, val name : String, val endSum : Double, val currentSum : Double)

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
                        Person(curs.getInt(0),
                            curs.getString(1),
                            curs.getString(2),
                            curs.getDouble(3),
                            curs.getDouble(4),
                        )
                    )
                }
                while (curs.moveToNext()) {
                    listOfPersons.add(
                        Person(curs.getInt(0),
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
                    val person = Person(curs.getInt(0),curs.getString(1),curs.getString(2),curs.getDouble(3), curs.getDouble(4))
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

        fun writeIncomeData(context: Context, income: Income){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME, Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("date",income.date)
                contentValues.put("who",income.who)
                contentValues.put("sum",income.sum)
                contentValues.put("where",income.where)
                contentValues.put("comment",income.comment)
                database.insert("Income",null,contentValues)
                database.close()
            }
        }

        fun updateIncomeData(context: Context, income: Income){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME, Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("date",income.date)
                contentValues.put("who",income.who)
                contentValues.put("sum",income.sum)
                contentValues.put("where",income.where)
                contentValues.put("comment",income.comment)
                database.update("Income",contentValues,"id = '${income.id}'",null)
                database.close()
            }
        }

        fun readIncomesData(context: Context) : List<Income>{
            val listOfIncomes = mutableListOf<Income>()
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val curs = database.rawQuery("SELECT * FROM Incomes;", null)
                if(curs.moveToFirst()) {
                    listOfIncomes.add(
                        Income(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(6),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
                while (curs.moveToNext()) {
                    listOfIncomes.add(
                        Income(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(6),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
                curs.close()
                database.close()
            }
            return listOfIncomes
        }

        fun readIncomeData(context: Context, personId: Int) : Income?{
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val curs = database.rawQuery("SELECT * FROM Incomes WHERE who = "+personId+";",null)
                if(curs.moveToFirst()){
                    val income = Income(curs.getInt(0),
                        curs.getString(1),
                        curs.getInt(2),
                        curs.getDouble(6),
                        curs.getString(4),
                        curs.getString(5)
                    )
                    curs.close()
                    database.close()
                    return income
                }
                database.close()
            }
            return null
        }

        fun readIncomesDataByDate(context: Context, date : String) : List<Income>{
            val listOfIncomes = mutableListOf<Income>()
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen) {
                val curs = database.rawQuery("SELECT * FROM Incomes WHERE date = '$date';", null)
                if (curs.moveToFirst()) {
                    listOfIncomes.add(
                        Income(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(6),
                            curs.getString(4),
                            curs.getString(5)
                        ))
                }
                while (curs.moveToNext()) {
                    listOfIncomes.add(
                        Income(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(6),
                            curs.getString(4),
                            curs.getString(5)
                        ))
                }
                curs.close()
                database.close()
            }
            return listOfIncomes
        }

        fun deleteIncomeData(context: Context, incomeId : Int){
            if(incomeId == -1) return
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                database.delete("Incomes","id = $incomeId",null)
                database.close()
            }
        }

        fun writeDemandData(context: Context, demand: Demand){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("date",demand.date)
                contentValues.put("who",demand.who)
                contentValues.put("sum",demand.sum)
                contentValues.put("from",demand.from)
                contentValues.put("category",demand.category)
                database.insert("Demand",null,contentValues)
                database.close()
            }
        }

        fun updateDemandData(context: Context, demand: Demand){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("date",demand.date)
                contentValues.put("who",demand.who)
                contentValues.put("sum",demand.sum)
                contentValues.put("from",demand.from)
                contentValues.put("category",demand.category)
                database.update("Demand",contentValues,"id = '${demand.id}'",null)
                database.close()
            }
        }

        fun readDemandsData(context: Context) : List<Demand>{
            val listOfDemands = mutableListOf<Demand>()
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val curs = database.rawQuery("SELECT * FROM Demands;", null)
                if(curs.moveToFirst()) {
                    listOfDemands.add(
                        Demand(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(3),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
                while (curs.moveToNext()) {
                    listOfDemands.add(
                        Demand(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(3),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
            }
            database.close()
            return listOfDemands.toList()
        }

        fun readDemandsDataByDate(context: Context, date : String) : List<Demand> {
            val listOfDemands = mutableListOf<Demand>()
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val curs = database.rawQuery("SELECT * FROM Demands WHERE dateOfDemand = '$date';", null)
                if(curs.moveToFirst()) {
                    listOfDemands.add(
                        Demand(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(3),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
                while (curs.moveToNext()) {
                    listOfDemands.add(
                        Demand(curs.getInt(0),
                            curs.getString(1),
                            curs.getInt(2),
                            curs.getDouble(3),
                            curs.getString(4),
                            curs.getString(5)
                        )
                    )
                }
            }
            database.close()
            return listOfDemands.toList()
        }

        fun writeTargetData(context: Context,target: Target){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("photoUri",target.photoUri)
                contentValues.put("name",target.name)
                contentValues.put("endSum",target.endSum)
                contentValues.put("currentSum",target.currentSum)
                database.insert("Targets",null,contentValues)
                database.close()
            }
        }

        fun updateTargetData(context: Context,target: Target){
            val database = context.openOrCreateDatabase(DATA_BASE_NAME,Context.MODE_PRIVATE,null)
            if(database.isOpen){
                val contentValues = ContentValues()
                contentValues.put("photoUri",target.photoUri)
                contentValues.put("name",target.name)
                contentValues.put("endSum",target.endSum)
                contentValues.put("currentSum",target.currentSum)
                database.update("Targets",contentValues,"id = '${target.id}'",null)
                database.close()
            }
        }
    }
}