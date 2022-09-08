package emg.example.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val userName = intent.getStringExtra(Constants.USER_NAME)
        val textViewName = findViewById<TextView>(R.id.tv_name)
        textViewName.text = userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val textViewScore = findViewById<TextView>(R.id.tv_score)
        textViewScore.text = "Your score is $correctAnswers out of $totalQuestions"

        val buttonFinnish = findViewById<Button>(R.id.btn_finish)
        buttonFinnish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}