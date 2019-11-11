package com.robelseyoum3.kotlinmvpcalculator.domain.repository

interface IValidator {

    //This part of the program can operate synchronously
    fun validateExpression(expression:String): Boolean

}