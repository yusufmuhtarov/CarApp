package com.example.carapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.carapp.databinding.ActivityMainBinding
import com.example.carapp.databinding.ActivitySave2Binding
import com.example.carapp.databinding.SavedCarItemBinding
import com.google.android.material.snackbar.Snackbar

class SaveActivity : AppCompatActivity() {
    private val binding: ActivitySave2Binding by lazy {
        ActivitySave2Binding.inflate(layoutInflater)
    }
    private val sharedPreferences: carCashManager by lazy {
        carCashManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.saveCard.setOnClickListener {
            saveCar()
        }
        binding.backCard.setOnClickListener {
            finish()
        }
    }


    fun saveCar() = binding.apply {
        if (carMarkEt.text?.isNotEmpty() == true && carModelEt.text?.isNotEmpty() == true) {
            sharedPreferences.saveCar(
                CarModel(
                    Mark = carMarkEt.text.toString(),
                    Model = carModelEt.text.toString()
                )
            )
            startActivity(Intent(this@SaveActivity, MainActivity::class.java))
        } else showToastMessage("Enter the fields")
        binding.backCard.setOnClickListener {
            startActivity(Intent(this@SaveActivity, MainActivity::class.java))
        }
    }


    fun showToastMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}

