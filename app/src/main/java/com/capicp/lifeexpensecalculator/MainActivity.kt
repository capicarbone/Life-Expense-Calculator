package com.capicp.lifeexpensecalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val workHourRate:Float = 5.0f
    val usdVefRate:Float = 32000f

    lateinit var vefExpenseET:EditText
    lateinit var usdExpenseTV:TextView
    lateinit var lifeExpenseTV:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vefExpenseET = findViewById(R.id.vef_expense)
        usdExpenseTV = findViewById(R.id.usd_expense)
        lifeExpenseTV = findViewById(R.id.life_expense)

        vefExpenseET.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(edit: Editable?) {

                if ( edit != null && edit.length > 0){
                    val vefValue:Float = edit.toString().toFloat()

                    val usdValue = vefValue / usdVefRate

                    val hoursValue = usdValue/workHourRate

                    usdExpenseTV.setText("$${"%.2f".format(usdValue)}")
                    lifeExpenseTV.setText("${"%.1f".format(hoursValue)}")


                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
      }
}


