package net.appsynth.basic

import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time_fighter.*

class TimeFighterActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "log-timefighter"
        private const val KEY_SCORE = "kScore"
        private const val KEY_TIME_LEFT = "kTimeLeft"
    }

    private lateinit var countDownTimer: CountDownTimer
    private var countDownInterval: Long = 1000
    private var score = 0
    private var timeLeft = 60
    private var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_fighter)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE, 0)
            timeLeft = savedInstanceState.getInt(KEY_TIME_LEFT, 0)
            restoreGame()
        } else {
            resetGame()
        }

        tabButton.setOnClickListener {
            if (gameStarted) {
                incrementScore()
            } else {
                startGame()
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY_SCORE, score)
        outState?.putInt(KEY_TIME_LEFT, timeLeft)
        countDownTimer.cancel()
    }

    private fun incrementScore() {
        score++
        val newScore = getString(R.string.score, score.toString())
        scoreTextView.text = newScore
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        val endScore = getString(R.string.score, score.toString())
        Toast.makeText(this, endScore, Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        score = 0
        val initialScore = getString(R.string.score, score.toString())
        scoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.timeleft, timeLeft.toString())
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(timeLeft * 1000L, countDownInterval) {

            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000
                val timeLeftString = getString(R.string.timeleft, timeLeft.toString())
                timeLeftTextView.text = timeLeftString
            }
        }

        gameStarted = false
    }

    private fun restoreGame() {
        val restoreScore = getString(R.string.score, score.toString())
        scoreTextView.text = restoreScore

        val restoreTimeLeft = getString(R.string.timeleft, timeLeft.toString())
        timeLeftTextView.text = restoreTimeLeft

        countDownTimer = object : CountDownTimer(timeLeft * 1000L, countDownInterval) {

            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000
                val timeLeftString = getString(R.string.timeleft, timeLeft.toString())
                timeLeftTextView.text = timeLeftString
            }
        }

        countDownTimer.start()
        gameStarted = true
    }
}
