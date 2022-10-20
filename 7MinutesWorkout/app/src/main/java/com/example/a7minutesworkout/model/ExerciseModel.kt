package com.example.a7minutesworkout.model

data class ExerciseModel(
    var id: Int = 0,
    var name: String? = null,
    var image: Int = 0,
    var isCompleted: Boolean = false,
    var isSelected: Boolean = false,
)