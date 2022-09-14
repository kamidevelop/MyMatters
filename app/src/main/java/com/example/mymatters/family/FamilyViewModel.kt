package com.example.mymatters.family

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymatters.mymattersdatabase.MyMattersDatabase
import com.example.mymatters.mymattersdatabase.Person

class FamilyViewModel(app : Application) : AndroidViewModel(app) {
    private val _persons = MutableLiveData<List<Person>>().apply {
        value = MyMattersDatabase.readFamilyData(this@FamilyViewModel.getApplication())
    }

    val persons : LiveData<List<Person>> = _persons
    fun writePerson(person: Person){
        MyMattersDatabase.writeInFamilyData(person,this@FamilyViewModel.getApplication())
        val mList = _persons.value?.toMutableList()
        mList?.add(person)
        _persons.value = mList?.toList()
    }
}