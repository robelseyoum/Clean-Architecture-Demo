package com.robelseyoum3.kotlinmvpcalculator.domain

import io.reactivex.Flowable

interface BaseUseCase<T> {
    fun execute(expression: String): Flowable<T>
}