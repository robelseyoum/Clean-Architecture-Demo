package com.robelseyoum3.kotlinmvpcalculator.util.scheduler

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun getComputationScheduler(): Scheduler

    fun getUiScheduler(): Scheduler

    //MAKE SURE YOU EXECUTE Rx calls on the Schedulers.trampoline()

}