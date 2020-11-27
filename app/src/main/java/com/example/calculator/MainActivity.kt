package com.example.calculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val calculator = Calculator()
    var flagdot = false
    var flagfun = false
    var flagroot = false


    private fun btnPressed (obj: View) {
        if (calculator.currentOperation == '=') {
            btnClearPressed(obj)
        }

        calculator.str = textView.text.toString()
        if (calculator.howMuchOp > calculator.curHowMuchOp)
        {
            textView.text = ""
        }
        val tag = obj.tag
        calculator.addNum(tag.toString()[0])

        if (textView.text.toString() == "0" && !flagdot)
            textView.text = "$tag"
        else
            if(textView.text.toString() == "√0" && !flagdot)
                textView.text = "√$tag"
            else
                textView.append("$tag")
        flagfun = false
    }



    private fun btnfunPressed (obj: View)
    {
        if(calculator.currentOperation != '=' && flagfun == false) {
            calculator.str = textView.text.toString()
            val tag = obj.tag
            calculator.giveCurOp(tag.toString()[0])
            if (calculator.howMuchOp != 1 && calculator.curHowMuchOp != 0)
            {
                textView.text = calculator.num1.toString()
            }
            flagdot = false
            flagfun = true
            flagroot = false
        }
    }

    private  fun btnEqualPressed(obj: View)
    {
        if(calculator.currentOperation != '=' && textView.text.length != 0) {
            val tag = obj.tag
            calculator.giveCurOp(tag.toString()[0])


            if((calculator.num1).toString().substringAfterLast('.') == "0")
                textView.setText(String.format("%.0f", calculator.num1))
            else {
                if ((calculator.num1).toString().substringAfterLast('.').length > 10)
                {
                    val num1round = calculator.num1.toString().substring(0, 11).toFloat()
                    textView.text = num1round.toString()
                }
                else
                {
                    textView.text = calculator.num1.toBigDecimal().toString()
                }
            }
            calculator.currentOperation = '='
        }
    }

    private fun btnRootPressed (obj: View)
    {
        button5.text = flagroot.toString()
        if(calculator.currentOperation != '=' && !flagroot) {
            if (calculator.howMuchOp > calculator.curHowMuchOp)
                textView.text = ""
            val tag = obj.tag
            textView.text = textView.text.padStart(textView.text.length+1, tag.toString()[0])
            calculator.giveOneOp(tag.toString()[0])
            flagroot = true
        }
    }

    private fun btnMinusPlusPressed(obj: View)
    {
        if(calculator.currentOperation != '=' && calculator.howMuchOp <= calculator.curHowMuchOp && textView.text.length != 0){

            if(calculator.num2>0)
                textView.text = textView.text.padStart(textView.text.toString().length+1, '-')
            else
                if(calculator.num2<0)
                    textView.text = textView.text.toString().substring(1, textView.text.toString().length)
            calculator.plusminus()
        }
    }


    private fun btnDotPressed (obj: View)
    {
        if(calculator.currentOperation != '=' && textView.text.toString() != "" && flagdot == false && textView.text.toString() != "√") {
            val tag = obj.tag
            calculator.addNum(tag.toString()[0])
            if (textView.text.toString().length != 0) {
                textView.append("$tag")
            }
            flagdot = true
        }
    }

    private  fun btnClearPressed(obj: View)
    {
        textView.text = ""
        flagfun = false
        flagdot = false
        flagroot = false
        calculator.CleanAll()
    }

    private  fun btnDeleteSymbPressed(obj: View)
    {
        if(calculator.currentOperation != '=' && textView.text.toString() != "")
        {
            if(textView.text[textView.text.length-1] == '.')
            {
                flagdot = false
            }
            textView.text = textView.text.substring(0,textView.text.length-1)
            calculator.DeleteSym()

        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener(::btnPressed)
        button2.setOnClickListener(::btnPressed)
        button3.setOnClickListener(::btnPressed)
        button4.setOnClickListener(::btnPressed)
        button5.setOnClickListener(::btnPressed)
        button6.setOnClickListener(::btnPressed)
        button7.setOnClickListener(::btnPressed)
        button8.setOnClickListener(::btnPressed)
        button9.setOnClickListener(::btnPressed)
        button0.setOnClickListener(::btnPressed)
        btnminus.setOnClickListener(::btnfunPressed)
        btnplus.setOnClickListener(::btnfunPressed)
        btnmult.setOnClickListener(::btnfunPressed)
        btnequal.setOnClickListener(::btnEqualPressed)
        btnroot.setOnClickListener(::btnRootPressed)
        btndot.setOnClickListener(::btnDotPressed)
        btndiv.setOnClickListener (::btnfunPressed)
        btnClear.setOnClickListener(::btnClearPressed)
        btnDeleteSymb.setOnClickListener(::btnDeleteSymbPressed)
        btnplusminus.setOnClickListener ( ::btnMinusPlusPressed)


    }


}