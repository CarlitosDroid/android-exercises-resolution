package com.example.android_exercises_resolution

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.steppers.IStepperAdapter
import com.example.steppers.Step
import com.example.steppers.StepStates
import com.example.steppers.VerticalStepperItemView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_view.view.*

class MainActivity : AppCompatActivity(), IStepperAdapter {

    private val stepList by lazy {
        mutableListOf(
            Step(
                getString(R.string.select_application),
                getString(R.string.select_first_step),
                StepStates.STATE_SELECTED
            ),
            Step(getString(R.string.setting_up_analytics), getString(R.string.the_second_step)),
            Step(getString(R.string.select_ad_format), getString(R.string.last_step))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vsvStepper.setStepperAdapter(this)
        vsvStepper.setStepList(stepList)
    }

    override fun getTitle(index: Int): CharSequence = stepList[index].title

    override fun getSummary(index: Int): CharSequence? = stepList[index].summary

    override fun size(): Int {
        return stepList.size
    }

    override fun onCreateCustomView(index: Int, context: Context, parent: VerticalStepperItemView): View {
        val inflated = LayoutInflater.from(context).inflate(R.layout.content_view, parent, false)

        inflated.btnOk.setOnClickListener {
            vsvStepper.setCurrentState(StepStates.STATE_SELECTED, index)
        }
        return inflated
    }
}
