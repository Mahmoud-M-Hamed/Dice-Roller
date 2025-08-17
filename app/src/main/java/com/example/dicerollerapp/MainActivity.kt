package com.example.dicerollerapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

enum class PlayersNumber(val value: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4)
}

class MainActivity : AppCompatActivity() {

    private lateinit var firstDice: ImageView
    private lateinit var secondDice: ImageView
    private lateinit var thirdDice: ImageView
    private lateinit var fourthDice: ImageView
    private lateinit var rollCounter: TextView
    private lateinit var rollButton: Button
    private val diceImages = DiceProvider.getDiceImages()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupInsets()
        setupActionBar()
        initializeViews()
        setupListeners()


    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.updatePadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = false
            }

            val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)


            v.setBackgroundColor(ContextCompat.getColor(this, R.color.custom_black))
            WindowInsetsCompat.CONSUMED

        }

    }


    private fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.white))
    }

    private fun initializeViews() {
        val playersNumber: Int = intent.getIntExtra("playersNumber", 0)

        rollButton = findViewById(R.id.roll_button)
        rollCounter = findViewById(R.id.dice_result)

        firstDice = findViewById(R.id.dice_3)
        secondDice = findViewById(R.id.dice_4)
        thirdDice = findViewById(R.id.dice_1)
        fourthDice = findViewById(R.id.dice_2)

        when (playersNumber) {
            PlayersNumber.ONE.value -> {
                firstDice.visibility = View.VISIBLE

            }

            PlayersNumber.TWO.value -> {
                firstDice.visibility = View.VISIBLE
                secondDice.visibility = View.VISIBLE

            }

            PlayersNumber.THREE.value -> {
                firstDice.visibility = View.VISIBLE
                secondDice.visibility = View.VISIBLE
                thirdDice.visibility = View.VISIBLE

            }

            PlayersNumber.FOUR.value -> {
                firstDice.visibility = View.VISIBLE
                secondDice.visibility = View.VISIBLE
                thirdDice.visibility = View.VISIBLE
                fourthDice.visibility = View.VISIBLE

            }
        }

    }

    private fun setupListeners() {
        var rollCount = 1

        rollButton.setOnClickListener {
            rollDice(rollCount++)
        }
    }

    private fun rollDice(rollCount: Int) {
        DiceRoller.roll(firstDice, diceImages)
        DiceRoller.roll(secondDice, diceImages)
        DiceRoller.roll(thirdDice, diceImages)
        DiceRoller.roll(fourthDice, diceImages)
        "You rolled: $rollCount".also { rollCounter.text = it }
    }

    // Handle back arrow click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed() // Go back to previous activity
            return true
        }
        return false
    }


}
