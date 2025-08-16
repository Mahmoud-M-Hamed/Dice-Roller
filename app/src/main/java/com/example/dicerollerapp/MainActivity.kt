package com.example.dicerollerapp

import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var rollCounter: TextView
    private lateinit var rollButton: Button
    private val diceImages = DiceProvider.getDiceImages()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val playersNumber: Int = intent.getIntExtra("playersNumber", 0)

        // First, find the container
        /*val firstDiceRow = findViewById<LinearLayout>(R.id.dice_row)
        val secondDiceRow = findViewById<LinearLayout>(R.id.dice_row2)*/

        setupInsets()
        setupActionBar()
        initializeViews()
        setupListeners()
        //updatePlayers(PlayersNumber.entries[playersNumber], firstDiceRow, secondDiceRow)
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
        supportActionBar?.show()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    private fun initializeViews() {
        rollButton = findViewById(R.id.roll_button)
        dice1 = findViewById(R.id.dice_3)
        dice2 = findViewById(R.id.dice_4)
        rollCounter = findViewById(R.id.dice_result)
    }

    private fun setupListeners() {
        var rollCount = 1

        rollButton.setOnClickListener {
            rollDice(rollCount++)
        }
    }

    private fun rollDice(rollCount: Int) {
        DiceRoller.roll(dice1, diceImages)
        DiceRoller.roll(dice2, diceImages)
        "You rolled: $rollCount".also { rollCounter.text = it }
    }

/*
    fun updatePlayers(playersNumber: PlayersNumber, firstDiceRow: LinearLayout, secondDiceRow: LinearLayout) {
        // Clear old images
        firstDiceRow.removeAllViews()

        // How many players?
        val count = when (playersNumber) {
            PlayersNumber.ONE -> 1
            PlayersNumber.TWO -> 2
            PlayersNumber.THREE -> 3
            PlayersNumber.FOUR -> 4
        }

        // Add images dynamically
        for (i in 1..count) {
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.dice_3) // replace with your player image
            imageView.layoutParams = LinearLayout.LayoutParams(
                150, // width
                150  // height
            ).apply {
                setMargins(16, 16, 16, 16) // add space between images
            }
            secondDiceRow.addView(imageView)
        }
    }
*/


}
