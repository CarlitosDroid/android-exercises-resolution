package com.example.steppers

import android.support.annotation.IntDef

class StepStates {
    @IntDef(StepStates.STATE_NORMAL, StepStates.STATE_SELECTED, StepStates.STATE_DONE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class StepState

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_SELECTED = 1
        const val STATE_DONE = 2
    }
}