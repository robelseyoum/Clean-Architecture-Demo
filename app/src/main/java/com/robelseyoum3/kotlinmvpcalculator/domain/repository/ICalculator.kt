package com.robelseyoum3.kotlinmvpcalculator.domain.repository

import com.robelseyoum3.kotlinmvpcalculator.data.datamodel.ExpressionDataModel
import io.reactivex.Flowable

interface  ICalculator{

    //operates asynchronously via Rxjava
    fun evaluateExpression(expression: String): Flowable<ExpressionDataModel>

}