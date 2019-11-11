package com.robelseyoum3.kotlinmvpcalculator.util.error

class ValidationException: Exception(){

    companion object{
        const val message = "Invalid Expression"
    }
}