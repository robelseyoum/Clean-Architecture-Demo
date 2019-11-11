package com.robelseyoum3.kotlinmvpcalculator.domain.domainmodel


//making constructor private mean, I don't want the constructor to be directly called
class Expression private constructor(var result: String,
                                     var successful: Boolean){
//you can only have 1 companion object per class
//static factory pattern
    companion object Factory{

        fun createSuccessModel(result: String): Expression {
            return Expression(result,
                true)
        }

        fun createFailureModel(result: String): Expression {
            return Expression(result,
                false)
        }
        //could even do createLoadingModel if appropriate. This App is simple enough that it doesn't need long running operations
    }
}