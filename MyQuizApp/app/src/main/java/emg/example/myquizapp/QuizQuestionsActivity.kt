package emg.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import emg.example.myquizapp.model.Question
import emg.example.myquizapp.service.QuestionsService
import javax.inject.Inject

@AndroidEntryPoint
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    private var progressBar: ProgressBar? = null
    private var textViewProgress: TextView? = null
    private var textViewQuestion: TextView? = null
    private var imageViewImage: ImageView? = null
    private var textViewOptionOne: TextView? = null
    private var textViewOptionTwo: TextView? = null
    private var textViewOptionThree: TextView? = null
    private var textViewOptionFour: TextView? = null
    private var buttonSubmit: Button? = null

    @Inject
    lateinit var questionsService: QuestionsService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.tv_progress)
        textViewQuestion = findViewById(R.id.tv_question)
        imageViewImage = findViewById(R.id.iv_image)
        textViewOptionOne = findViewById(R.id.tv_option_one)
        textViewOptionTwo = findViewById(R.id.tv_option_two)
        textViewOptionThree = findViewById(R.id.tv_option_three)
        textViewOptionFour = findViewById(R.id.tv_option_four)
        buttonSubmit = findViewById(R.id.btn_submit)

        textViewOptionOne?.setOnClickListener(this)
        textViewOptionTwo?.setOnClickListener(this)
        textViewOptionThree?.setOnClickListener(this)
        textViewOptionFour?.setOnClickListener(this)
        buttonSubmit?.setOnClickListener(this)

        mQuestionsList = questionsService.getQuestions()
        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionsView()
        mQuestionsList?.let { questionList ->
            val question = questionList[mCurrentPosition - 1]
            progressBar?.progress = mCurrentPosition
            textViewProgress?.text = "$mCurrentPosition / ${questionList.size}"
            imageViewImage?.setImageResource(question.image)
            textViewQuestion?.text = question.question
            textViewOptionOne?.text = question.optionOne
            textViewOptionTwo?.text = question.optionTwo
            textViewOptionThree?.text = question.optionThree
            textViewOptionFour?.text = question.optionFour
            if (mCurrentPosition == questionList.size) {
                buttonSubmit?.text = "FINISH"
            } else {
                buttonSubmit?.text = "SUBMIT"
            }
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, textViewOptionOne!!)
        options.add(1, textViewOptionTwo!!)
        options.add(2, textViewOptionThree!!)
        options.add(3, textViewOptionFour!!)

        options.forEach {
            it.setTextColor(Color.parseColor("#7A8089"))
            it.typeface = Typeface.DEFAULT
            it.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(textView: TextView?, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber
        textView?.setTextColor(Color.parseColor("#363A43"))
        textView?.setTypeface(textView.typeface, Typeface.BOLD)
        textView?.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(textViewOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(textViewOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(textViewOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(textViewOptionFour, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question?.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.incorrect_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question?.correctAnswer!!, R.drawable.correct_option_border_bg)
                    Log.d("question", "=> Question $mCurrentPosition of ${mQuestionsList!!.size}")
                    if (mCurrentPosition == mQuestionsList!!.size) {
                        buttonSubmit?.text = "FINISH"
                    } else {
                        buttonSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> textViewOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> textViewOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> textViewOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> textViewOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }
}