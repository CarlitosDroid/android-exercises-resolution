package com.example.android_exercises_resolution.utils

import android.support.v7.widget.AppCompatEditText
import android.view.animation.AnimationUtils
import com.example.android_exercises_resolution.R

fun AppCompatEditText.shake() {
    val shake = AnimationUtils.loadAnimation(this.context, R.anim.anim_shake)
    this.startAnimation(shake)
}

fun AppCompatEditText.isValid(validator: (String) -> Boolean, errorMessage: String): Boolean {
    if (!validator(this.text.toString())) {
        this.shake()
        return false
    }
    return true
}