package com.example.a7minutesworkout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.R
import com.example.a7minutesworkout.databinding.ItemExcerciseStatusBinding
import com.example.a7minutesworkout.model.ExerciseModel

class ExerciseStatusAdapter(val exercises: List<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemExcerciseStatusBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun render(exercise: ExerciseModel) {
            binding.tvItem.text = exercise.id.toString()
            when {
                exercise.isSelected -> {
                    binding.tvItem.background =
                        binding.root.context.getDrawable(R.drawable.item_circular_thin_color_accent_border)
                    binding.tvItem.setTextColor(Color.parseColor("#212121"))
                }
                exercise.isCompleted -> {
                    binding.tvItem.background =
                        binding.root.context.getDrawable(R.drawable.item_circular_color_accent_background)
                    binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
                else -> {
                    binding.tvItem.background =
                        binding.root.context.getDrawable(R.drawable.item_circular_color_gray_background)
                    binding.tvItem.setTextColor(Color.parseColor("#212121"))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExcerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = exercises[position]
        holder.render(model)
    }

    override fun getItemCount() = exercises.size

}
