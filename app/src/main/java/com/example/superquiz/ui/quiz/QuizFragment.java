package com.example.superquiz.ui.quiz;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.superquiz.R;
import com.example.superquiz.data.Question;
import com.example.superquiz.data.QuestionBank;
import com.example.superquiz.data.QuestionRepository;
import com.example.superquiz.databinding.FragmentQuizBinding;
import com.example.superquiz.databinding.FragmentWelcomeBinding;
import com.example.superquiz.injection.ViewModelFactory;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private QuizViewModel viewModel;
    private FragmentQuizBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(QuizViewModel.class);

        viewModel.startQuiz();

        viewModel.currentQuestion.observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {

                updateQuestion(question);

            }
        });

        binding.answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer1, 0);
            }
        });

        binding.answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer2, 1);
            }
        });

        binding.answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer3, 2);
            }
        });

        binding.answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer4, 3);
            }
        });
    }

    private void updateQuestion(Question question){

        binding.question.setText(question.getQuestion());
        binding.answer1.setText(question.getChoiceList().get(0));
        binding.answer2.setText(question.getChoiceList().get(1));
        binding.answer3.setText(question.getChoiceList().get(2));
        binding.answer4.setText(question.getChoiceList().get(3));
    }

    private void updateAnswer(Button button, Integer index){
        showAnswerValidity(button, index);
        enableAllAnswers(false);
        binding.next.setVisibility(View.VISIBLE);
    }

    private void showAnswerValidity(Button button, Integer index){
        Boolean isValid = viewModel.isAnswerValid(index);
        if (isValid) {
            button.setBackgroundColor(Color.parseColor("#388e3c")); // dark green
            binding.validityText.setText("Good Answer ! 💪");
        } else {
            button.setBackgroundColor(Color.RED);
            binding.validityText.setText("Bad answer 😢");
        }
        binding.validityText.setVisibility(View.VISIBLE);
    }

    private void enableAllAnswers(Boolean enable){
        List<Button> allAnswers = Arrays.asList(binding.answer1, binding.answer2, binding.answer3, binding.answer4);
        allAnswers.forEach( answer -> {
            answer.setEnabled(enable);
        });
    }
}