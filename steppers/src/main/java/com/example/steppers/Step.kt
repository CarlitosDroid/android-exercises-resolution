package com.example.steppers

data class Step(var title: String, var summary: String, var stepState: Int = StepStates.STATE_NORMAL)