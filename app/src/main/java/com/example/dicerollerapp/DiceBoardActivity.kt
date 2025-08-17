package com.example.dicerollerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class DiceBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dice_board)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = false
            }
            insets
        }

        val cards: List<MaterialCardView> = listOf(
            findViewById(R.id.choose_dice_board_first_card),
            findViewById(R.id.choose_dice_board_second_card),
            findViewById(R.id.choose_dice_board_third_card),
            findViewById(R.id.choose_dice_board_fourth_card)
        )

        val cardColors = listOf(
            ContextCompat.getColor(this, R.color.cream),
            ContextCompat.getColor(this, R.color.light_orange),
            ContextCompat.getColor(this, R.color.soft_pink),
            ContextCompat.getColor(this, R.color.peach)
        )

        val cardTitlesList = listOf("One Dice", "Two Dice", "Three Dice", "Four Dice")

        val cardDescriptionsList = listOf(
            "Suitable for single-player board games, roll one dice",
            "Suitable for multi-player board games, roll two dice",
            "Suitable for multi-player board games, roll three dice",
            "Suitable for multi-player board games, roll four dice"
        )

        var selectedIndex: Int? = null // keep track of selected card

        cards.forEachIndexed { index, card ->
            val cardTitle: TextView = card.findViewById(R.id.card_title)
            val cardDescription: TextView = card.findViewById(R.id.card_description)
            val cardImage: ImageView = card.findViewById(R.id.card_image)

            card.setCardBackgroundColor(cardColors[index])
            cardTitle.text = cardTitlesList[index]
            cardDescription.text = cardDescriptionsList[index]
            cardImage.setImageResource(DiceProvider.getDiceImages()[index])

            card.setOnClickListener {

                cards.forEachIndexed { i, c ->
                    c.setCardBackgroundColor(cardColors[i])
                    c.strokeWidth = 0
                }


                // Highlight selected card
                card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card.strokeColor = ContextCompat.getColor(this, R.color.orange)
                card.strokeWidth = 10
                selectedIndex = index
            }
        }

        val playButton: Button = findViewById(R.id.play_button)
        playButton.setOnClickListener {
            if (selectedIndex != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("playersNumber", selectedIndex + 1)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please Select a Card", Toast.LENGTH_SHORT).show()
            }
        }


    }
}