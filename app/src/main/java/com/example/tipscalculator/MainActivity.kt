package com.example.tipscalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //Instead of calling findViewById each time we use binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Instead of adding activity_main we use binding.root
        setContentView(binding.root)

        //To make our calculateButton to be performed we use setOnClickListener
        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
            //calling the id costOfService where the user input the given tips
        val stringInTextField = binding.costOfServiceEditText.toString()
            //Tips can be any currency so we convert the decimal into Double
            //If the number is null or double it returns its existing value
        val cost = stringInTextField.toDoubleOrNull() ?: return

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch?.isChecked
        if (roundUp == true) {
            tip = kotlin.math.ceil(tip)
        }

        //This gives you a number formatter you can use to format numbers as currency.
        NumberFormat.getCurrencyInstance()
        //To format the any currencies tips into any amount
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //To show the formatted currency onto the tip amount
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}


