package com.capicp.lifeexpensecalculator

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val workHourRate:Float = 5.0f
    var usdVefRate:Int = 87000

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

                updateCalculations()

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    fun updateCalculations(){

        val edit:Editable = vefExpenseET.text
        if ( edit != null && edit.length > 0){
            val vefValue:Float = edit.toString().toFloat()

            val usdValue = vefValue / usdVefRate

            val hoursValue = usdValue/workHourRate

            usdExpenseTV.setText("$${"%.2f".format(usdValue)}")
            lifeExpenseTV.setText("${"%.1f".format(hoursValue)}")
        }
    }

    override fun onResume() {
        super.onResume()

        val sp = getSharedPreferences(getString(R.string.sp_file_settings), Context.MODE_PRIVATE)
        usdVefRate = sp.getInt(getString(R.string.sp_usd_vef_rate), 80000)

        updateCalculations()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.settings -> {
                val int = Intent(this, SettingsActivity::class.java)
                startActivity(int)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}


