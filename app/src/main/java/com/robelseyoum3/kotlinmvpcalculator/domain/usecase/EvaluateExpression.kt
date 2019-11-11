package com.robelseyoum3.kotlinmvpcalculator.domain.usecase

import com.robelseyoum3.kotlinmvpcalculator.domain.BaseUseCase
import com.robelseyoum3.kotlinmvpcalculator.domain.domainmodel.Expression
import com.robelseyoum3.kotlinmvpcalculator.domain.repository.ICalculator
import com.robelseyoum3.kotlinmvpcalculator.domain.repository.IValidator
import com.robelseyoum3.kotlinmvpcalculator.util.error.ValidationException
import com.robelseyoum3.kotlinmvpcalculator.util.scheduler.BaseSchedulerProvider
import io.reactivex.Flowable


class EvaluateExpression (private val calculator: ICalculator,
                          private val validator: IValidator,
                          private val scheduler: BaseSchedulerProvider) :
        BaseUseCase<Expression> {

    //Validator operates synchronously because having to much Rx stuff here seems to freak
    //people out. Also, it really doesn't need to be thread.

    //Not every part of your app needs to be represented as a Data stream.
    //In fact, if it excutes fine synchronouly, its not necessary at all to represent as a stream

    override fun execute(expression: String): Flowable<Expression> {

        if(validator.validateExpression(expression)) {

            return calculator.evaluateExpression(expression)
                //Note: result is something I declare, but it's type comes from the type
                //which we are observing, which is ExpressionDataModel (see return type of ICalculator.kt)
                .flatMap { result ->
                    Flowable.just(
                        Expression.createSuccessModel(result.value)
                    )
                }
                .subscribeOn(
                    scheduler.getComputationScheduler()
                )
        }

        return Flowable.just(
            Expression.createFailureModel(ValidationException.message)
        )
    }

}