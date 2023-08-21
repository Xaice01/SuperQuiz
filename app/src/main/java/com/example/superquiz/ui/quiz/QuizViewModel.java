package com.example.superquiz.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.superquiz.data.Question;
import com.example.superquiz.data.QuestionRepository;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private QuestionRepository questionRepository;
    private List<Question> listQuestions;
    private Integer currentQuestionIndex=0;



    public QuizViewModel(QuestionRepository questionRepository)
    {
        this.questionRepository=questionRepository;
    }
    MutableLiveData<Question> currentQuestion = new MutableLiveData<Question>();
    MutableLiveData<Integer> score= new MutableLiveData<Integer>(0);
    MutableLiveData<Boolean> isLastQuestion = new MutableLiveData<Boolean>(false);
    public void startQuiz(){
        listQuestions=questionRepository.getQuestions();
        currentQuestion.postValue(listQuestions.get(0));
    }

    public Boolean isAnswerValid(Integer answerIndex){
        Question question =currentQuestion.getValue();

        Boolean isValid = question != null && question.getAnswerIndex()==answerIndex;
        Integer currentScore = score.getValue();
        if(currentScore != null && isValid)
        {
            score.setValue(currentScore +1);
        }

        return isValid;
    }

    public void nextQuestion(){
        Integer nextIndex = currentQuestionIndex +1;

        if(nextIndex >= listQuestions.size()){
            return; // should not happened as the 'Next' button is not  displayed at the last question
        } else if (nextIndex==listQuestions.size()-1) {
            isLastQuestion.postValue(true);
        }
        currentQuestionIndex =nextIndex;
        currentQuestion.postValue(listQuestions.get(currentQuestionIndex));
    }
}
