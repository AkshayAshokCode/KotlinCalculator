package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var lastDot = false
    var lastEqual = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        if (lastEqual) {
            tvInput.text = ""
            tvOutput.text = ""
            lastEqual = false
        }
        tvInput.append((view as Button).text)
        lastNumeric = true
        tvOutput.text = ""
    }

    fun onClear(view: View) {
        tvInput.text = ""
        tvOutput.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var first = splitValue[0]
                    var second = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val output = (first.toDouble() - second.toDouble()).toString()
                    val splitOutput = output.split(".")
                    if (splitOutput[1] == "0") {
                        tvOutput.text = splitOutput[0]
                    } else {
                        tvOutput.text = output
                    }
                    lastEqual = true
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var first = splitValue[0]
                    var second = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val output = (first.toDouble() + second.toDouble()).toString()
                    val splitOutput = output.split(".")
                    if (splitOutput[1] == "0") {
                        tvOutput.text = splitOutput[0]
                    } else {
                        tvOutput.text = output
                    }
                    lastEqual = true
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var first = splitValue[0]
                    var second = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val output = (first.toDouble() * second.toDouble()).toString()
                    val splitOutput = output.split(".")
                    if (splitOutput[1] == "0") {
                        tvOutput.text = splitOutput[0]
                    } else {
                        tvOutput.text = output
                    }
                    lastEqual = true
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var first = splitValue[0]
                    var second = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val output = (first.toDouble() / second.toDouble()).toString()
                    val splitOutput = output.split(".")
                    if (splitOutput[1] == "0") {
                        tvOutput.text = splitOutput[0]
                    } else {
                        tvOutput.text = output
                    }
                    lastEqual = true
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
        }

    }
}