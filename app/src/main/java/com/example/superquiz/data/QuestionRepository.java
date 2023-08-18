package com.example.superquiz.data;

import java.util.Arrays;
import java.util.List;

public class QuestionRepository {

    private static QuestionBank questionBank;
    public QuestionRepository(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    public List<Question> getQuestions() {
        return questionBank.getQuestions();
    }
}
