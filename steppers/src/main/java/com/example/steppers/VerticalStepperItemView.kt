package com.example.steppers

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.vertical_stepper_item_view.view.*

class VerticalStepperItemView : FrameLayout {

    constructor (context: Context) : super(context)

    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        View.inflate(context, R.layout.vertical_stepper_item_view, this)
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setSummary(summary: String) {
        tvSummary.text = summary
    }

    fun setColorStep() {
        tvStepNumber.setBackgroundResource(R.drawable.bg_step_enable)
    }

    fun addCustomContentView(view: View) {
        flContent.addView(view)
    }

    fun setState(@StepStates.StepState stepState: Int){
        when(stepState){
            StepStates.STATE_SELECTED -> {
                tvStepNumber.setBackgroundResource(R.drawable.bg_step_enable)
                flContent.visibility = View.VISIBLE
            }

            StepStates.STATE_NORMAL -> {
                tvStepNumber.setBackgroundResource(R.drawable.bg_step_disable)
                flContent.visibility = View.GONE
            }

            StepStates.STATE_DONE -> {
                tvStepNumber.setBackgroundResource(R.drawable.bg_step_enable)
                flContent.visibility = View.GONE
            }
        }
    }

}