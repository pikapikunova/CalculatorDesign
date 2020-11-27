package com.example.calculator

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Console
import kotlin.math.sqrt

class Calculator(Num1: Double, Num2: Double, Str: String)
{
    var num1: Double
        private set
    var num2: Double
        private set
    var str: String
    var howMuchOp = 0
    var curHowMuchOp = 0
    var currentOperation: Char = '0'
    var previousOperation: Char = '0'
    private var stroke = ""
    var rootflag = false
    var plusminusflag = false

    init {
        num1 = Num1
        num2 = Num2
        str = Str
    }
    constructor(): this(0.0, 0.0,"")

    operator fun plus(num: Double) = num1 + num
    operator fun times(num: Double)= num1 * num
    operator fun div (num: Double)= num1 / num
    operator fun minus(num: Double)= num1 - num
    fun root()= sqrt(num2)

    fun addNum(char: Char){
        if(howMuchOp > curHowMuchOp) {
            previousOperation = currentOperation
            curHowMuchOp++
        }
        stroke += char
        num2 = stroke.toDouble()
    }


    fun Calc():Double {
        when(previousOperation)
        {
            '+' -> return plus(num2)
            '*'-> return times(num2)
            '/'-> if(num2 != 0.0) {
                return div(num2)
            }
            else
                return 1000000000000000000000000.0
            '-'-> return minus(num2)
        }
        return 100000000000000000000000000.0
    }

    fun giveCurOp(char : Char){
        if (str.length !=0) {
            currentOperation = char
        }


        if(rootflag == true) {
            if (plusminusflag)
                num2 = -root()
            else
                if (num2 >= 0)
                    num2 = root()
                else
                    num2 = 1000000000000000000000000.0
        }


        if(howMuchOp == 0)
        {
            ShiftValue()
        }

        if (howMuchOp !=0)
        {
            num1 = Calc()
        }

        howMuchOp++
        stroke = ""
        rootflag = false
        plusminusflag = false
    }

    fun giveOneOp(char : Char){
        rootflag = true
        if(howMuchOp > curHowMuchOp) {
            previousOperation = currentOperation
            curHowMuchOp++
        }
    }

    fun plusminus(){
        if(stroke != "")
        {
            if(rootflag)
            {
                plusminusflag = true
            }
            else {
                if (num2 > 0) {
                    stroke = '-' + stroke
                } else
                    if (num2 < 0)
                        stroke = stroke.substring(1, stroke.length)
                num2 = stroke.toDouble()
            }

        }


    }

    fun ShiftValue()
    {
        val temp = num1
        num1 = num2
        num2 = temp
    }

    fun CleanAll()
    {
        num1 = 0.0
        num2 = 0.0
        str = ""
        stroke = ""
        howMuchOp = 0
        curHowMuchOp = 0
        currentOperation = '0'
        previousOperation = '0'
        rootflag = false
        plusminusflag = false
    }

    fun DeleteSym()
    {
        if(stroke.length!=0)
            if(stroke.length == 1)
            {
                stroke = ""
                num2 = 0.0
            }
            else {
                stroke = stroke.substring(0, stroke.length - 1)
                num2 = stroke.toDouble()
            }

    }


}

//консольная проверка
/*fun main(args: Array<String>) {
    val a = number(6.0)
    val b = number(7.5)


    println((a+b).num)
    println((a-b).num)
    println((a*b).num)
    println(a.root().num)
    a.ShiftValue(b)
    println(a.num)
    println(b.num)
    a.addNum('3')
    println(a.num)
}*/