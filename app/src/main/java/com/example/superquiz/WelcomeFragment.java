package com.example.superquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superquiz.databinding.FragmentWelcomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;


    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.playButton.setEnabled(false);

        binding.usernameEditText.addTextChangedListener(new TextWatcher(){
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }

          @Override
          public void onTextChanged(CharSequence s, int Start, int before, int count){

          }

          @Override
          public void afterTextChanged(Editable s){
            binding.playButton.setEnabled(!s.toString().isEmpty());
          }
        });

        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The user just clicked
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuizFragment quizFragment = QuizFragment.newInstance();
                fragmentTransaction.replace(R.id.container, quizFragment);
                fragmentTransaction.commit();
            }
        });

    }

}