package com.example.a7minutesworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemExcerciseStatusBinding
import com.example.a7minutesworkout.model.ExerciseModel

class ExerciseStatusAdapter(val exercises: List<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemExcerciseStatusBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun render(exercise: ExerciseModel) {
            binding.tvItem.text = exercise.id.toString()
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
