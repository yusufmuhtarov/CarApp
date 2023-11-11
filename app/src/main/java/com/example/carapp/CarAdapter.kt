package com.example.carapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carapp.databinding.SavedCarItemBinding

class CarAdapter(
    private val onDeleteCarClick: (index:Int)-> Unit,
) : RecyclerView.Adapter<CarAdapter.CarAppViewHolder>() {

    private val carList = mutableListOf<CarModel>()

    fun updateList(CarList: List<CarModel>) {
        carList.clear()
        carList.addAll(CarList)
        notifyDataSetChanged()
    }

    inner class CarAppViewHolder(
        private val binding: SavedCarItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CarModel) {
            binding.carMarkTv.text = model.Mark
            binding.carModelTv.text = model.Model
            binding.deleteCarBtm.setOnClickListener{
                onDeleteCarClick.invoke(carList.indexOf(model))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarAppViewHolder {
        val binding = SavedCarItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.saved_car_item, parent,
                    false)
        )
        return CarAppViewHolder(binding)
    }

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(
        holder: CarAppViewHolder,
        position: Int)
    {
        holder.bind(carList[position])
    }
}
