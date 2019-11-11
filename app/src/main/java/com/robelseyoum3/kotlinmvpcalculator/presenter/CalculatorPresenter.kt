package com.robelseyoum3.kotlinmvpcalculator.presenter

import com.robelseyoum3.kotlinmvpcalculator.domain.domainmodel.Expression
import com.robelseyoum3.kotlinmvpcalculator.domain.usecase.EvaluateExpression
import com.robelseyoum3.kotlinmvpcalculator.util.scheduler.BaseSchedulerProvider
import com.robelseyoum3.kotlinmvpcalculator.view.IViewContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

class CalculatorPresenter(private var view: IViewContract.View,
                          private var viewModel: IViewContract.ViewModel,
                          private val scheduler: BaseSchedulerProvider,
                          private val eval: EvaluateExpression) : IViewContract.Presenter {

    private val eventStream = CompositeDisposable()

    private val EMPTY = ""

    //Update the state, then the view
    override fun onEvaluateClick(expression: String) {

        //Presenter is the Observer
        eventStream.add(
            eval.execute(expression)
                .observeOn(scheduler.getUiScheduler())
                .subscribeWith(object : DisposableSubscriber<Expression>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: Expression?) {
                        if(t!!.successful){
                            viewModel.setDisplayState(t.result)
                        } else {
                            view.showError(t.result)
                        }
                    }

                    override fun onError(t: Throwable?) {
                        restartFeature()
                    }

                })
        )
    }

    override fun onInputButtonClick(value: String) {
        viewModel.setDisplayState(
            viewModel.getDisplayState() + value
        )

    }

    override fun onDeleteClick() {
        viewModel.setDisplayState(
            viewModel.getDisplayState().dropLast(1)
        )
    }

    override fun onLongDeleteClick() {
        viewModel.setDisplayState(EMPTY)

    }

    private fun restartFeature() {
        eventStream.clear()
        view.restartFeature()
    }

    override fun bind() {
        eventStream.add(
            //Darel's suggestion was to make publisher
        viewModel.getDisplayStatePublisher()
            .subscribeWith(object : DisposableSubscriber<String>(){
                override fun onComplete() {
                }

                override fun onNext(t: String) {
                    view.setDisplay(t)
                }
                override fun onError(t: Throwable?) {
                }
            })
        )

    }

    override fun clear() {
        eventStream.clear()
    }

}