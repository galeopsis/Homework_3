package com.galeopsis.homework_3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences("key", 0)
        val spEdit = sp?.edit()

        tvOperation.text = sp?.getString("operation", "")
        tvResult.text = sp?.getString("result", "")

        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }
        btn_leftbracket.setOnClickListener { setTextFields("(") }
        btn_rightbracket.setOnClickListener { setTextFields(")") }
        btn_dot.setOnClickListener { setTextFields(".") }
        btn_minus.setOnClickListener { setTextFields("-") }
        btn_plus.setOnClickListener { setTextFields("+") }
        btn_multiply.setOnClickListener { setTextFields("*") }
        btn_divide.setOnClickListener { setTextFields("/") }

        btn_ac.setOnClickListener {
            tvOperation.text = ""
            tvResult.text = ""
            spEdit?.clear()
            spEdit?.apply()
        }

        btn_back.setOnClickListener {
            val str = tvOperation.text.toString()
            if (str.isNotEmpty())
                tvOperation.text = str.substring(0, str.length - 1)
            tvResult.text = ""
        }

        btn_result.setOnClickListener {
            try {
                val exStr = tvOperation.text.toString()
                val ex = ExpressionBuilder(exStr).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    tvResult.text = longRes.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Ошибка!", "Сообщение: ${e.message}")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val sp = getSharedPreferences("key", 0)
        val spEdit = sp?.edit()
        spEdit?.putString("operation", tvOperation.text.toString())
        spEdit?.putString("result", tvResult.text.toString())
        spEdit?.apply()
    }

    fun setTextFields(str: String) {
        if (tvResult.text != "") {
            tvOperation.text = tvResult.text
            tvResult.text = ""
        }
        tvOperation.append(str)
    }
}