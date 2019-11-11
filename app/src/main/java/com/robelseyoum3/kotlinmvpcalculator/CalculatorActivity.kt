package com.robelseyoum3.kotlinmvpcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robelseyoum3.kotlinmvpcalculator.dependencyinjection.Injector
import com.robelseyoum3.kotlinmvpcalculator.view.CalculatorFragment

class CalculatorActivity : AppCompatActivity() {

    companion object {
        val VIEW: String = "VIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        addFragment()
    }

    private fun addFragment() {
        var fragmentManager = supportFragmentManager
        //var fragmentTransaction = fragmentManager.beginTransaction()

        var fragmentTransaction = fragmentManager.findFragmentByTag(VIEW) as CalculatorFragment?
            ?: CalculatorFragment.newInstance(Injector(this))


        //fragmentTransaction.add(R.id.fragment_container_from_main, CalculatorFragment()).commit()
        fragmentManager.beginTransaction().replace(R.id.fragment_container_from_main, fragmentTransaction).commit()
    }
}
