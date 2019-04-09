package com.example.android_exercises_resolution.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS
            .matcher(this.trim()).matches()