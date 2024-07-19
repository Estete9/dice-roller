package com.example.dicerollerapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val diceImage: ImageView = findViewById(R.id.diceBtn)

        diceImage.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        CoroutineScope(Dispatchers.Main).launch {
            val animationDuration = 3000L
            val startTime = System.currentTimeMillis()

            while(System.currentTimeMillis() - startTime < animationDuration) {
                val diceRoll = (1..6).random()

                val diceDrawable = when(diceRoll) {
                    1 -> R.drawable.dice_one
                    2 -> R.drawable.dice_two
                    3 -> R.drawable.dice_three
                    4 -> R.drawable.dice_four
                    5 -> R.drawable.dice_five
                    else -> R.drawable.dice_six
                }

                diceImage.setImageResource(diceDrawable)

                delay(200L)
            }
        }
    }
}