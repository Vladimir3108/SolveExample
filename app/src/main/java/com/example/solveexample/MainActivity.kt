package com.example.solveexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.solveexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    var examples = 0
    val operands = arrayOf("+","-","*","/")
    var operand = ""
    var operator1 = 0
    var operator2 = 0

    fun checkResult(operand:String,operatorOne:Int,operatorTwo:Int, playerResult: Int):Boolean
    {
        var result = 0
        when (operand){
            "+" -> result = operatorOne+operatorTwo
            "-" -> result = operatorOne-operatorTwo
            "*" -> result = operatorOne*operatorTwo
            "/" -> result = operatorOne/operatorTwo
        }
        return (result == playerResult)
    }

    fun onClickStart(view: View)
    {
        binding.TxtEdit.text.clear()
        binding.TxtExample.setBackgroundColor(Color.WHITE)
        operand = operands.random()
        operator1 = (10..99).random()
        operator2 = (10..99).random()
        if (operand == "/")
        {
            while (operator1%operator2!=0)
            {
                operator1 = (10..99).random()
                operator2 = (10..99).random()
            }
        }
        binding.TxtExample.text = operator1.toString() + " " + operand + " " + operator2.toString() + " = "
        binding.TxtEdit.isEnabled = true
        binding.BtnCheck.isEnabled = true
        binding.BtnStart.isEnabled = false
    }

    fun onClickCheckAnswer(view: View)
    {
        var win = binding.TxtRight.text.toString().toDouble()
        var lose = binding.TxtWrong.text.toString().toInt()
        val player = binding.TxtEdit.text.toString().toInt()
        var percentage = 0.0
        binding.BtnCheck.isEnabled = false
        binding.BtnStart.isEnabled = true
        if (!checkResult(operand,operator1,operator2,player))
        {
            lose +=1
            examples +=1
            percentage = (examples/win).toDouble()
            binding.TxtWrong.text = lose.toString()
            binding.TxtExample.setBackgroundColor(Color.RED)
            binding.TxtAll.text = examples.toString()
            binding.TxtPercentage.text = ("%.2f".format(percentage)).toString()  + "%"
        }
        else
        {
            win += 1
            examples += 1
            percentage = (win/examples*100).toDouble()
            binding.TxtRight.text = win.toString()
            binding.TxtExample.setBackgroundColor(Color.GREEN)
            binding.TxtAll.text = examples.toString()
            binding.TxtPercentage.text = ("%.2f".format(percentage)).toString()  + "%"
        }
    }
}