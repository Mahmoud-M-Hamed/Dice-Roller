package com.example.dicerollerapp

import android.widget.ImageView

object DiceRoller {
    fun roll(diceView: ImageView, diceImages: List<Int>) {
        diceView.setImageResource(diceImages.random())
    }
}
