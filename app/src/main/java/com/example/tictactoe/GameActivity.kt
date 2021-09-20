package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    lateinit var imageView0_0: ImageView
    lateinit var imageView0_1: ImageView
    lateinit var imageView0_2: ImageView
    lateinit var imageView1_0: ImageView
    lateinit var imageView1_1: ImageView
    lateinit var imageView1_2: ImageView
    lateinit var imageView2_0: ImageView
    lateinit var imageView2_1: ImageView
    lateinit var imageView2_2: ImageView
    lateinit var resetButton: Button
    var activePlayer = 1
    var counter: Int = 0
    val winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    lateinit var imageViewOnClickListener: View.OnClickListener
    lateinit var textViewPlayerTurn: TextView
    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Player representation
        // 0 - X
        // 1 - O

        supportActionBar?.setTitle("Play Game")


        imageView0_0 = findViewById(R.id.image_view_0_0)
        imageView0_1 = findViewById(R.id.image_view_0_1)
        imageView0_2 = findViewById(R.id.image_view_0_2)
        imageView1_0 = findViewById(R.id.image_view_1_0)
        imageView1_1 = findViewById(R.id.image_view_1_1)
        imageView1_2 = findViewById(R.id.image_view_1_2)
        imageView2_0 = findViewById(R.id.image_view_2_0)
        imageView2_1 = findViewById(R.id.image_view_2_1)
        imageView2_2 = findViewById(R.id.image_view_2_2)
        textViewPlayerTurn = findViewById(R.id.player_turn)
        resetButton = findViewById(R.id.reset_button)


        if (gameActive) {
            imageViewOnClickListener = View.OnClickListener {
                if (it is ImageView) {

                    playGame(it)
                }
            }

            imageView0_0.setOnClickListener(imageViewOnClickListener)
            imageView0_1.setOnClickListener(imageViewOnClickListener)
            imageView0_2.setOnClickListener(imageViewOnClickListener)
            imageView1_0.setOnClickListener(imageViewOnClickListener)
            imageView1_1.setOnClickListener(imageViewOnClickListener)
            imageView1_2.setOnClickListener(imageViewOnClickListener)
            imageView2_0.setOnClickListener(imageViewOnClickListener)
            imageView2_1.setOnClickListener(imageViewOnClickListener)
            imageView2_2.setOnClickListener(imageViewOnClickListener)

        }

        resetButton.setOnClickListener {
            gameReset()
        }
    }

    fun playGame(imageView: ImageView) {
        val tappedImage: Int = imageView.getTag().toString().toInt()
        // game reset function will be called
        // if someone wins or the boxes are full
        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset()
        }

        // if the tapped image is empty

        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap
            counter++

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false
            }

            // mark this position
            gameState[tappedImage] = activePlayer

            // this will give a motion
            // effect to the image
            imageView.setTranslationY(-1000f)

            // change the active player
            // from 0 to 1 or 1 to 0
            if (activePlayer == 0) {
                // set the image of x
                imageView.setImageResource(R.drawable.cross_sign)
                activePlayer = 1

                // change the status
                textViewPlayerTurn.text = getString(R.string.O_turn_to_play)
            } else {
                // set the image of o
                imageView.setImageResource(R.drawable.zero)
                activePlayer = 0

                // change the status
                textViewPlayerTurn.text = getString(R.string.x_turn_to_play)
            }
            imageView.animate().translationYBy(1000f).setDuration(300)
        }
        var flag = 0
        // Check if any player has won
        // Check if any player has won
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                flag = 1

                // Somebody has won! - Find out who!
                var winnerStr: String

                // game reset function be called
                gameActive = false
                winnerStr = if (gameState[winPosition[0]] == 0) {
                    "X has won"
                } else {
                    "O has won"
                }
                // Update the status bar for winner announcement
                textViewPlayerTurn.text = winnerStr
            }
        }
        // set the status if the match draw
        // set the status if the match draw
        if (counter == 9 && flag == 0) {
            textViewPlayerTurn.text = getString(R.string.match_draw)
        }
    }

    // reset the game
    fun gameReset() {
        gameActive = true
        activePlayer = 0
        counter = 0
        for (i in 0..gameState.size - 1) {
            gameState[i] = 2
        }
        // remove all the images from the boxes inside the grid
        imageView0_0.setImageResource(0)
        imageView0_1.setImageResource(0)
        imageView0_2.setImageResource(0)
        imageView1_0.setImageResource(0)
        imageView1_1.setImageResource(0)
        imageView1_2.setImageResource(0)
        imageView2_0.setImageResource(0)
        imageView2_1.setImageResource(0)
        imageView2_2.setImageResource(0)
        textViewPlayerTurn.text = getString(R.string.x_turn_to_play)
    }

}
