package com.example.steppers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.vertical_stepper_view.view.*

class VerticalStepperView : FrameLayout {

    private var stepperAdapter: IStepperAdapter? = null

    private val itemAdapter = ItemAdapter()

    private var stepList: MutableList<Step>? = null

    constructor (context: Context) : super(context)

    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        View.inflate(context, R.layout.vertical_stepper_view, this)
    }

    fun setStepList(stepList: MutableList<Step>) {
        this.stepList = stepList
        itemAdapter.notifyDataSetChanged()
    }

    fun setStepperAdapter(mStepperAdapter: IStepperAdapter) {
        this.stepperAdapter = mStepperAdapter
        rvStepperView.adapter = itemAdapter
    }

    fun setCurrentState(@StepStates.StepState stepState: Int, position: Int) {
        for (step in stepList!!) {
            step.stepState = StepStates.STATE_NORMAL
        }
        if(position < stepList!!.size -1){
            stepList!![position+1].stepState = stepState
            itemAdapter.notifyDataSetChanged()
        }
    }

    private inner class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder =
            ItemViewHolder(VerticalStepperItemView(parent.context))

        override fun getItemCount(): Int = stepperAdapter!!.size()

        override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
            itemViewHolder.bind(stepList!![position])
        }

        inner class ItemViewHolder(var verticalStepperItemView: VerticalStepperItemView) :
            RecyclerView.ViewHolder(verticalStepperItemView) {
            fun bind(step: Step) {
                verticalStepperItemView.setTitle(step.title)
                verticalStepperItemView.setSummary(step.summary)
                verticalStepperItemView.setColorStep()
                verticalStepperItemView.setState(step.stepState)

                val customView =
                    stepperAdapter!!.onCreateCustomView(position, context, verticalStepperItemView)
                verticalStepperItemView.addCustomContentView(customView)
            }
        }
    }
}