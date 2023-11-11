package com.example.carapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.carapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: carCashManager by lazy {
        carCashManager(this)
    }
    private val adapter: CarAdapter by lazy {
        CarAdapter(onDeleteCarClick = {index ->
            sharedPreferences.deleteNoteAtIndex(index)
            setUpViewsAndAdapter()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpClickListener()
        setUpViewsAndAdapter()
    }

    private fun setUpClickListener() = binding.apply {
        addNoteBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SaveActivity::class.java))
        }
        deleteCard.setOnClickListener {
            showConfirmDeleteDialog()
        }
    }

    private fun setUpViewsAndAdapter() {
        val listOfCar = sharedPreferences.getCar()
        if (listOfCar.isNotEmpty()) {
            binding.mainRv.visibility = View.VISIBLE
            adapter.updateList(listOfCar)
            binding.mainRv.adapter = adapter
            binding.firstNoteIv.visibility = View.GONE
        }
        else{
            binding.mainRv.visibility = View.GONE
            binding.firstNoteIv.visibility = View.VISIBLE
        }

    }

    private fun showConfirmDeleteDialog() {
        val alertDialog = MaterialAlertDialogBuilder(this)
        alertDialog.setMessage("Do you really want to delete all cares")
        alertDialog.setPositiveButton("yes") { dialog, _ ->
            deleteAllSavedNotes()
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.create().show()
    }

    private fun deleteAllSavedNotes() {
        sharedPreferences.deleteCar()
        adapter.updateList(emptyList())
        binding.mainRv.visibility = View.GONE
        binding.firstNoteIv.visibility = View.VISIBLE
    }
}