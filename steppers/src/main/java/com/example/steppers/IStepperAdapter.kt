package com.example.steppers

import android.content.Context
import android.view.View

interface IStepperAdapter {

    fun getTitle(index: Int): CharSequence

    fun getSummary(index: Int): CharSequence?

    fun size(): Int

    fun onCreateCustomView(index: Int, context: Context, parent: VerticalStepperItemView): View

}