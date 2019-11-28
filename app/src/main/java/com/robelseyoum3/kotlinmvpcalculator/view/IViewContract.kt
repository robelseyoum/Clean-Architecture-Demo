package com.robelseyoum3.kotlinmvpcalculator.view

import io.reactivex.Flowable

interface IViewContract {



    interface View {
        fun getCurrentExpression(): String
        fun setDisplay(value: String)
        fun showError(value: String)
        fun restartFeature()
    }

    interface ViewModel {
        fun setDisplayState(result: String)

        //Get something (Flowable in this case)
        // which will emit a CalcUIModel as soon as it is set (above method)
        fun getDisplayStatePublisher(): Flowable<String>

        fun getDisplayState():String
    }

    interface Presenter {
        fun onEvaluateClick(expression:String)
        fun onInputButtonClick(value: String)
        fun onDeleteClick()
        fun onLongDeleteClick()
        fun bind()
        fun clear()
    }

}


//When do I want to use an enum class? Enums are great for creating a restricted set of values
//examples - Constants, Primitive Values
enum class Inputs (val value:Char){
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ZERO('0'),
    DECIMAL('.'),
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('*'),
    DIVIDE('/')
}


//When do I want to use a sealed class? When I want to create a restricted class/type heirarchy
sealed class ViewEvent<out T> {
    object OnStart : ViewEvent<Nothing>()
    object OnDestroy : ViewEvent<Nothing>()
    data class OnOperandClick<out Inputs>(val char:Inputs) : ViewEvent<Inputs>()
    data class OnOperatorClick<out Inputs>(val char:Inputs) : ViewEvent<Inputs>()
    object OnEvaluateClick : ViewEvent<Nothing>()
    object OnDeleteClick: ViewEvent<Nothing>()
    object OnLongDeleteClick: ViewEvent<Nothing>()
}
