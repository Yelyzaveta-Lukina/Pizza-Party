/**
 * Created by Yelyzaveta Lukina on 05/25/2025
 */

package edu.lukina.pizzaparty

import kotlin.math.ceil
// Defines a constant for the number of slices per pizza.
const val SLICES_PER_PIZZA = 8

// Defines a class to calculate the number of pizzas needed for a party.
class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {
    // Stores the number of people attending the party.
    var partySize = 0
        // Custom setter to ensure partySize is not negative.
        set(value) {
            field = if (value >= 0) value else 0
        }
    // Defines an enum for different hunger levels, each associated with a number of slices.
    enum class HungerLevel(var numSlices: Int) {
        // Represents a light hunger level, consuming 2 slices.
        LIGHT(2),
        // Represents a medium hunger level, consuming 3 slices.
        MEDIUM(3),
        // Represents a ravenous hunger level, consuming 4 slices.
        RAVENOUS(4)
    }
    // Calculates the total number of pizzas needed.
    val totalPizzas: Int
        // Custom getter to calculate pizzas based on party size, hunger level, and slices per pizza.
        get() {
            return ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
        }

    // Initialization block to set the initial party size.
    init {
        this.partySize = partySize
    }
}