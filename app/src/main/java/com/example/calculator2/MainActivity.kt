package com.example.calculator2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.text.DecimalFormat
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    clearBTN.setOnClickListener {
        input.text = ""
        output.text = ""
        backspaceBTN.isClickable = true
    }

    bracketLeftBTN.setOnClickListener {
        input.text = addToInputText("(")
    }

    bracketRightBTN.setOnClickListener {
        input.text = addToInputText(")")
    }

    zeroBTN.setOnClickListener {
        input.text = addToInputText("0")
    }

    oneBTN.setOnClickListener {
        input.text = addToInputText("1")
    }

    twoBTN.setOnClickListener {
        input.text = addToInputText("2")
    }

    threeBTN.setOnClickListener {
        input.text = addToInputText("3")
    }

    fourBTN.setOnClickListener {
        input.text = addToInputText("4")
    }

    fiveBTN.setOnClickListener {
        input.text = addToInputText("5")
    }

    sixBTN.setOnClickListener {
        input.text = addToInputText("6")
    }

    sevenBTN.setOnClickListener {
        input.text = addToInputText("7")
    }

    eightBTN.setOnClickListener {
        input.text = addToInputText("8")
    }

    nineBTN.setOnClickListener {
        input.text = addToInputText("9")
    }

    pointBTN.setOnClickListener {
        input.text = addToInputText(".")
    }

    divideBTN.setOnClickListener {
        input.text = addToInputText("÷")
    }

    multiplyBTN.setOnClickListener {
        input.text = addToInputText("×")
    }

    subtractBTN.setOnClickListener {
        input.text = addToInputText("-")
    }

    addBTN.setOnClickListener {
        input.text = addToInputText("+")
    }

    equalBTN.setOnClickListener {
        showResult()
    }


}
    

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            showResult()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
            showResult()
        }
    }


    private fun getInputExpression(): String {
    var expression = input.text.replace(Regex("÷"), "/")
    expression = expression.replace(Regex("×"), "*")
    return expression
}

private fun showResult() {
    try {
        val expression = getInputExpression()
        val result = Expression(expression).calculate()
        if (result.isNaN()) {
            // Show Error Message
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            // Show Result
            output.text = DecimalFormat("0.######").format(result).toString()
            output.setTextColor(ContextCompat.getColor(this, R.color.green))
            backspaceBTN.isClickable = false

        }
    } catch (e: Exception) {
        // Show Error Message
        output.text = "Error"
        output.setTextColor(ContextCompat.getColor(this, R.color.red))
    }
}

private fun addToInputText(buttonValue: String): String {
    return "${input.text}$buttonValue"
}

fun backSpaceAction(view: android.view.View) {
    val length = input.length()
    if (length > 0)
        input.text = input.text.subSequence(0, length - 1)
}
}