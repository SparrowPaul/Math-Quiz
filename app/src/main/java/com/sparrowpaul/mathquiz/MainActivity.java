package com.sparrowpaul.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView number1TextView;
    private TextView number2TextView;
    private TextView resultTextView;
    private Button submitButton;
    private EditText userInputEditText;
    private int answer;
    private int count;
    private int rightAnswerCount;
    private int wrongAnswerCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findingIDs();
        generateQuestions();
        verifyUserAnswer();

    }

    private void verifyUserAnswer(){ // function to verify user answer
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(userInputEditText.getText().toString())){
                    Toast.makeText(MainActivity.this, "Answer can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    int userAnswer = Integer.parseInt(userInputEditText.getText().toString());

                    if (answer == userAnswer){
                        Toast.makeText(getApplicationContext(), "You are right", Toast.LENGTH_SHORT).show();
                        generateQuestions();
                        rightAnswerCount++;
                        checkAttempts();
                        clearUserInput();
                    }else{
                        Toast.makeText(getApplicationContext(), "You are wrong", Toast.LENGTH_SHORT).show();
                        generateQuestions();
                        wrongAnswerCount++;
                        checkAttempts();
                        clearUserInput();
                    }
                }
            }
        });
    }

    private void findingIDs(){ // function for finding ids
        number1TextView = findViewById(R.id.number1ID);
        number2TextView = findViewById(R.id.number2ID);
        resultTextView = findViewById(R.id.resultMessageID);
        submitButton = findViewById(R.id.submitButtonID);
        userInputEditText = findViewById(R.id.userInputID);
    }

    private void generateQuestions(){ // function to generate questions
        int randomNumber1 = (int) (Math.random() * 10);
        int randomNumber2 = (int) (Math.random() * 10);

        number1TextView.setText(String.valueOf(randomNumber1));
        number2TextView.setText(String.valueOf(randomNumber2));

        answer = randomNumber1 + randomNumber2;

        count ++;

    }

    private void checkAttempts(){ //Function to check the number of attempts
        if (count >5){
            submitButton.setEnabled(false);
            submitButton.setText(R.string.game_over);
            quizSummary();

        }
    }

    private void quizSummary(){ // displays the summary of the quiz
        String summaryMessage = "Thanks for playing Math Quiz. You had "
                +rightAnswerCount+" right answer(s) and "+wrongAnswerCount+" wrong answer(s)";

        resultTextView.setText(summaryMessage);
    }

    private void clearUserInput(){  // clears user input
        userInputEditText.getText().clear();
    }

}
