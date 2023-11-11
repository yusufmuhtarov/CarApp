package com.example.carapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"
const val SHARED_PREF = "YUSUF"

class carCashManager(
    private val context: Context,
){
    private val sharedPreference  = context.getSharedPreferences(
        SHARED_PREFERENCE_KEY,
        Context.MODE_PRIVATE
    )

    fun getCar(): List<CarModel> {
        val gson = Gson()
        val json = sharedPreference.getString(SHARED_PREF,null)
        val type : Type = object : TypeToken<ArrayList<CarModel?>?>() {}.type
        val carList = gson.fromJson<ArrayList<CarModel>>(json,type)
        return carList?: emptyList()
    }

    fun saveCar(carModel: CarModel) {
        val notes = getCar().toMutableList()
        notes.add(0, carModel)
        val notesGson = Gson().toJson(notes)
        sharedPreference
            .edit().putString(SHARED_PREF,notesGson)
            .apply()
        Log.d("jcnwr","${carModel.Model}")
    }

    fun deleteCar(){
    sharedPreference.edit().clear().apply()
    }

    fun deleteNoteAtIndex(index: Int) {
        val getAllCars = getCar().toMutableList()
        if (index in 0 until getAllCars.size) {
            getAllCars.removeAt(index)
            val carGsonString = Gson().toJson(getAllCars)
            sharedPreference.edit().putString(SHARED_PREF, carGsonString).apply()
        }
    }
}

