package com.robelseyoum3.kotlinmvpcalculator.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.robelseyoum3.kotlinmvpcalculator.data.CalculatorImpl
import com.robelseyoum3.kotlinmvpcalculator.data.ValidatorImpl
import com.robelseyoum3.kotlinmvpcalculator.domain.usecase.EvaluateExpression
import com.robelseyoum3.kotlinmvpcalculator.presenter.CalculatorPresenter
import com.robelseyoum3.kotlinmvpcalculator.util.scheduler.SchedulerProviderImpl
import com.robelseyoum3.kotlinmvpcalculator.view.CalculatorFragment
import com.robelseyoum3.kotlinmvpcalculator.view.IViewContract
import com.robelseyoum3.kotlinmvpcalculator.viewmodel.CalculatorViewModel

class Injector(private var activity: AppCompatActivity) {

    private var validator: ValidatorImpl = ValidatorImpl
    private var calculator: CalculatorImpl = CalculatorImpl
    private var schedulerProvider: SchedulerProviderImpl = SchedulerProviderImpl



    fun providePresenter(view: CalculatorFragment): IViewContract.Presenter {

        return CalculatorPresenter(
            view,
            ViewModelProviders.of(activity).get(CalculatorViewModel::class.java),
            schedulerProvider,
            EvaluateExpression(calculator, validator, schedulerProvider)
        )
    }


}