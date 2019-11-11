package com.robelseyoum3.kotlinmvpcalculator.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProviderImpl: BaseSchedulerProvider {

    override fun getComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    override fun getUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()

    }

}