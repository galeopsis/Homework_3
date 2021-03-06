package com.galeopsis.homework_3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fathzer.soft.javaluator.DoubleEvaluator
import com.galeopsis.homework_3.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val keyOperation = "OPERATION"
    private val evaluator = DoubleEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {

            if (savedInstanceState != null) {
                tvOperation.text = savedInstanceState.getCharSequence(keyOperation)
            }

            btn0.setOnClickListener { setTextFields("0") }
            btn1.setOnClickListener { setTextFields("1") }
            btn2.setOnClickListener { setTextFields("2") }
            btn3.setOnClickListener { setTextFields("3") }
            btn4.setOnClickListener { setTextFields("4") }
            btn5.setOnClickListener { setTextFields("5") }
            btn6.setOnClickListener { setTextFields("6") }
            btn7.setOnClickListener { setTextFields("7") }
            btn8.setOnClickListener { setTextFields("8") }
            btn9.setOnClickListener { setTextFields("9") }
            btnLeftBracket.setOnClickListener { setTextFields("(") }
            btnRightBracket.setOnClickListener { setTextFields(")") }
            btnDot.setOnClickListener { setTextFields(".") }
            btnMinus.setOnClickListener { setTextFields("-") }
            btnPlus.setOnClickListener { setTextFields("+") }
            btnMultiply.setOnClickListener { setTextFields("*") }
            btnDivide.setOnClickListener { setTextFields("/") }
            btnSin.setOnClickListener { setTextFields("sin") }
            btnCos.setOnClickListener { setTextFields("cos") }
            btnShutdown.setOnClickListener { exitProcess(0) }
            btnSqrt.setOnClickListener { sqrt() }
            btnResult.setOnClickListener { evaluate() }
            btnAC.setOnClickListener { acButton() }
            btnBack.setOnClickListener { delete() }
        }
    }

    private fun ActivityMainBinding.acButton() {
        tvOperation.text = ""
    }

    private fun ActivityMainBinding.delete() {
        val str = tvOperation.text.toString()
        if (str.isNotEmpty())
            tvOperation.text = str.substring(0, str.length - 1)
    }

    private fun ActivityMainBinding.sqrt() {
        try {
            val exStr = tvOperation.text.toString()
            val result = kotlin.math.sqrt(exStr.toDouble())
            val doubleRes = result.toLong()
            tvOperation.text = if (result == doubleRes.toDouble()) doubleRes.toString()
            else result.toString()
        } catch (e: Exception) {
            Log.d("????????????!", "??????????????????: ${e.message}")
        }
    }

    private fun ActivityMainBinding.evaluate() {
        try {
            val expression = tvOperation.text.toString()
            val result = evaluator.evaluate(expression)
            val doubleRes = result.toLong()
            tvOperation.text = if (result == doubleRes.toDouble()) doubleRes.toString()
            else result.toString()
        } catch (e: Exception) {
            Log.d("????????????!", "??????????????????: ${e.message}")
        }
    }

    private fun ActivityMainBinding.setTextFields(str: String) {
        tvOperation.append(str)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence(keyOperation, binding.tvOperation.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getCharSequence(keyOperation)
    }
}

