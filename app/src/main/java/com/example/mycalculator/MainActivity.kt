package com.example.mycalculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener { setTextFields("0")}
        binding.btn1.setOnClickListener { setTextFields("1")}
        binding.btn2.setOnClickListener { setTextFields("2")}
        binding.btn3.setOnClickListener { setTextFields("3")}
        binding.btn4.setOnClickListener { setTextFields("4")}
        binding.btn5.setOnClickListener { setTextFields("5")}
        binding.btn6.setOnClickListener { setTextFields("6")}
        binding.btn7.setOnClickListener { setTextFields("7")}
        binding.btn8.setOnClickListener { setTextFields("8")}
        binding.btn9.setOnClickListener { setTextFields("9")}
        binding.minusBtn.setOnClickListener { setTextFields("-")}
        binding.plusBtn.setOnClickListener { setTextFields("+")}
        binding.divideBtn.setOnClickListener { setTextFields("/")}
        binding.multiplyBtn.setOnClickListener { setTextFields("*")}
        binding.bracketOpenBtn.setOnClickListener { setTextFields("(")}
        binding.bracketCloseBtn.setOnClickListener { setTextFields(")")}

        binding.clearBtn.setOnClickListener {
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }

        binding.backBtn.setOnClickListener {
            val str = binding.mathOperation.text.toString()
            if (str.isNotEmpty()){
                binding.mathOperation.text = str.substring(0, str.length - 1)
            }
            binding.resultText.text = ""
        }

        binding.equalBtn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble()) {
                    binding.resultText.text = longRes.toString()
                } else {
                    binding.resultText.text = result.toString()
                }

            } catch(e:Exception) {
                Log.d("Mistake", "Message, ${e.message}")
            }
        }
    }

    private fun setTextFields(str: String) {
        if(binding.resultText.text != ""){
            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }
}