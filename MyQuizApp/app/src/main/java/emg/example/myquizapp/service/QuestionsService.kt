package emg.example.myquizapp.service

import emg.example.myquizapp.model.Question

interface QuestionsService {
    fun getQuestions(): ArrayList<Question>
}