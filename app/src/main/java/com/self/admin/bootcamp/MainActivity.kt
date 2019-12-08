package com.self.admin.bootcamp

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.getting_started.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val rollHistory = Stack<Int>(HISTORY_SIZE)
    private var currentDie = DEFAULT_DIE_SIDE
    private lateinit var currentPlayer: Player
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getting_started)
//        task1Init()
//        task2Init()
//        task3Init()

        task4Init()
        task5Init()
    }

//    private fun task1Init() {
//        updateBtnInit()
//    }

//    private fun task2Init() {
//        rollBtnInit()
//        revertBtnInit()
//    }

//    private fun task3Init() {
//        rollBtnInit()
//        revertBtnInit()
//    }

    private fun task4Init() {
        player1 = Player("Player1")
        player2 = Player("Player2")
        currentPlayer = player1
        setScore()
        maxScore.text = "Max Score: $GAME_MAX_SCORE"
        rollBtnInit()
        resetBtnInit()
    }

    private fun task5Init() {
        navbarBtn.setOnClickListener {
            if (navigation.visibility == View.VISIBLE) {
                Handler().post { navigation.visibility = View.GONE }
            } else {
                navigation.visibility = View.VISIBLE
            }
        }
    }

//    private fun updateBtnInit() {
//        updateBtn.setOnClickListener {
//            textBox.text = textInput.text
//            textInput.text.clear()
//        }
//        updateBtn.setOnLongClickListener {
//            textInput.text.clear()
//            textBox.text = getString(R.string.default_text)
//            true
//        }
//    }

//    private fun rollBtnInit() {
//        /*Sometimes it will look like the number is lagging behind. This is so because
//        * the sample size is small. Therefore the probability of having the same value as before
//        * is higher which visually looks like the program is skipping a number when pressing the
//        * roll button.*/
//        rollBtn.setOnClickListener {
//            val side = Random.nextInt(1, 6)
//            rollHistory.push(numberView.text.toString().toInt())
//            numberView.text = side.toString()
//            revertBtn.isEnabled = true
//            println(rollHistory.toString())
//        }
//        rollBtn.setOnLongClickListener {
//            numberView.text = DEFAULT_DIE_SIDE.toString()
//            rollHistory.clear()
//            revertBtn.isEnabled = false
//            true
//        }
//    }
//
//    private fun revertBtnInit() {
//        revertBtn.isEnabled = false
//        revertBtn.setOnClickListener {
//            numberView.text = rollHistory.pop().toString()
//            println(rollHistory.toString())
//            if (rollHistory.isEmpty()) {
//                revertBtn.isEnabled = false
//            }
//        }
//    }


//    private fun rollBtnInit() {
//        /*Sometimes it will look like the number is lagging behind. This is so because
//        * the sample size is small. Therefore the probability of having the same value as before
//        * is higher which visually looks like the program is skipping a number when pressing the
//        * roll button.*/
//        rollBtn.setOnClickListener {
//            if (currentDie != 0) {
//                rollHistory.push(currentDie)
//                revertBtn.isEnabled = true
//            }
//            rollProgress.visibility = View.VISIBLE
//            currentDie = Random.nextInt(1, 6)
//            imageView.setImageResource(getDieImage(currentDie))
//            Handler().postDelayed({ rollProgress.visibility = View.GONE}, 1000)
//            rollProgress.visibility = View.INVISIBLE
//            currentPlayer.points += currentDie
//            if(currentPlayer.points >= textBox.text.toString().toInt()){
//                Toast.makeText(applicationContext,
//                    "You Won: ${currentPlayer.name}",
//                    Toast.LENGTH_LONG)
//            }else{
//                currentPlayer = if(currentPlayer == player1) player2 else player1
//            }
//        }
//        rollBtn.setOnLongClickListener {
//            imageView.setImageResource(getDieImage(DEFAULT_DIE_SIDE))
//            currentPlayer = player1
//            player1.points = 0
//            player2.points = 0
//            rollHistory.clear()
//            revertBtn.isEnabled = false
//            true
//        }
//    }
//
//    private fun revertBtnInit() {
//        revertBtn.isEnabled = false
//        revertBtn.setOnClickListener {
//            imageView.setImageResource(getDieImage(rollHistory.pop() ?: 0))
//            println(rollHistory.toString())
//            if (rollHistory.isEmpty()) {
//                revertBtn.isEnabled = false
//            }
//        }
//    }

    private fun rollBtnInit() {
        gameRollBtn.setOnClickListener {
            gameRollProgress.visibility = View.VISIBLE
            currentDie = Random.nextInt(1, 6)
            currentPlayer.points += currentDie
            Handler().postDelayed({ gameRollProgress.visibility = View.GONE }, 500)
            gameImageView.setImageResource(getDieImage(currentDie))
            setScore()
            if (currentPlayer.points < GAME_MAX_SCORE) {
                currentPlayer = switchPlayer()
            } else {
                maxScore.text = "Winner ${currentPlayer.name}"
                gameResetBtn.visibility = View.VISIBLE
                gameRollBtn.visibility = View.GONE

            }
        }
    }

    private fun resetBtnInit() {
        gameResetBtn.setOnClickListener {
            gameImageView.setImageResource(getDieImage(DEFAULT_DIE_SIDE))
            player1.points = 0
            player2.points = 0
            setScore()
            currentPlayer = player1
            maxScore.text = "Max Score: $GAME_MAX_SCORE"
            gameResetBtn.visibility = View.GONE
            gameRollBtn.visibility = View.VISIBLE
        }
    }

    private fun getDieImage(side: Int) = when (side) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice
    }

    private fun setScore() {
        player1View.text = player1.toString()
        player2View.text = player2.toString()
    }

    private fun switchPlayer(): Player =
        if (currentPlayer == player1) player2 else player1


    companion object {
        private const val DEFAULT_DIE_SIDE = 0
        private const val HISTORY_SIZE = 3
        private const val GAME_MAX_SCORE = 12
    }
}
