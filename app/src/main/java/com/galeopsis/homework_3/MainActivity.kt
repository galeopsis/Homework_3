package com.galeopsis.homework_3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fathzer.soft.javaluator.DoubleEvaluator
import com.galeopsis.homework_3.databinding.ActivityMainBinding
import kotlin.math.sqrt
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val keyOperation = "OPERATION"
    private val keyResult = "RESULT"
    private val evaluator = DoubleEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {

            if (savedInstanceState != null) {
                tvOperation.text = savedInstanceState.getCharSequence(keyOperation)
                tvResult.text = savedInstanceState.getCharSequence(keyResult)
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

            btnSqrt.setOnClickListener {
                try {
                    val exStr = tvOperation.text.toString()
                    val result = sqrt(exStr.toDouble())
                    val longRes = result.toLong()
                    tvOperation.text = ""
                    if (result == longRes.toDouble())
                        tvResult.text = longRes.toString()
                    else
                        tvResult.text = result.toString()
                } catch (e: Exception) {
                    Log.d("Ошибка!", "Сообщение: ${e.message}")
                }
            }

            btnResult.setOnClickListener {
                try {
                    val expression = tvOperation.text.toString()
                    val expResult = evaluator.evaluate(expression)
                    val longRes = expResult.toLong()
                    if (expResult == longRes.toDouble())
                        tvResult.text = longRes.toString()
                    else
                        tvResult.text = expResult.toString()
                } catch (e: Exception) {
                    Log.d("Ошибка!", "Сообщение: ${e.message}")
                }
            }

            btnAC.setOnClickListener {
                tvOperation.text = ""
                tvResult.text = ""
            }

            btnBack.setOnClickListener {
                val str = tvOperation.text.toString()
                if (str.isNotEmpty())
                    tvOperation.text = str.substring(0, str.length - 1)
                tvResult.text = ""
            }
        }
    }

    private fun setTextFields(str: String) {
        if (binding.tvResult.text != "") {
            binding.tvOperation.text = binding.tvResult.text
            binding.tvResult.text = ""
        }
        binding.tvOperation.append(str)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence(keyOperation, binding.tvOperation.text)
        outState.putCharSequence(keyResult, binding.tvResult.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getCharSequence(keyOperation)
        savedInstanceState.getCharSequence(keyResult)
    }
}

