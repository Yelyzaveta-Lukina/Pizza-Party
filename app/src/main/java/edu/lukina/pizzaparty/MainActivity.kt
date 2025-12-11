/**
 *Created by Yelyzaveta Lukina on 05/25/2025
 */

package edu.lukina.pizzaparty

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Tag for logging purposes.
const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

// Main activity for the pizza party application.
class MainActivity : AppCompatActivity() {

    // EditText for the number of attendees.
    private lateinit var numAttendEditText: EditText
    // TextView to display the calculated number of pizzas.
    private lateinit var numPizzasTextView: TextView
    // RadioGroup to select the hunger level.
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0

    // Called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity.
        setContentView(R.layout.activity_main)
        // Initialize the EditText for the number of attendees.
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        // Initialize the TextView to display the number of pizzas.
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        // Initialize the RadioGroup for hunger level selection.
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
        // Log that onCreate has been called.
        Log.d(TAG, "onCreate was called")
    }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }
    private fun displayTotal() {

        // Format the string to display the total number of pizzas.
        val totalText = getString(R.string.total_pizzas_num,totalPizzas)
        numPizzasTextView.text = totalText
    }

    // Called when the "Calculate" button is clicked.
    fun calculateClick(view: View) {
        // Get the text that was typed into the EditText.
        val numAttendStr = numAttendEditText.text.toString()

        // Log that calculateClick has been called and the number of attendees string.
        Log.d (TAG, "calculateClick was called $numAttendStr")

        // Convert the text into an integer or 0 if input is an empty string
        val numAttend = numAttendStr.toIntOrNull() ?:0

        // Determine how many slices on average each person will eat based on the selected radio button.
        val hungerLevel = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Create a PizzaCalculator instance with the number of attendees and hunger level.
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        // Get the total number of pizzas calculated.
        displayTotal()
    }
}