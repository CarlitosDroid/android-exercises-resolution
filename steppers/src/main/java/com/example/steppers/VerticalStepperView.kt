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
    private var stepDefault = StepStates.STATE_NORMAL

    constructor (context: Context) : super(context)

    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        View.inflate(context, R.layout.vertical_stepper_view, this)
        rvStepperView.adapter = itemAdapter
        setCurrentState(stepDefault)
    }

    fun setStepperAdapter(mStepperAdapter: IStepperAdapter) {
        this.stepperAdapter = mStepperAdapter
        itemAdapter.notifyDataSetChanged()
    }

    fun setCurrentState(@StepStates.StepState stepState: Int) {
        this.stepDefault = stepState
    }

    private inner class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder =
            ItemViewHolder(VerticalStepperItemView(parent.context))

        override fun getItemCount(): Int = stepperAdapter!!.size()

        override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
            itemViewHolder.verticalStepperItemView.setTitle(stepperAdapter!!.getTitle(position).toString())
            itemViewHolder.verticalStepperItemView.setSummary(stepperAdapter!!.getSummary(position).toString())
            itemViewHolder.verticalStepperItemView.setColorStep()
            itemViewHolder.verticalStepperItemView.setState(stepDefault)

            val customView =
                stepperAdapter!!.onCreateCustomView(position, context, itemViewHolder.verticalStepperItemView)
            itemViewHolder.verticalStepperItemView.addCustomContentView(customView)
        }

        inner class ItemViewHolder(var verticalStepperItemView: VerticalStepperItemView) :
            RecyclerView.ViewHolder(verticalStepperItemView)
    }

}