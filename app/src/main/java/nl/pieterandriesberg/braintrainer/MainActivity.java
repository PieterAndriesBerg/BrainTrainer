package nl.pieterandriesberg.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

/*TODO: Create levels to make it more difficult
    Buttons on the home screen with levels.
    Higher difficulty could be ( higher numbers)
    Divide
    Subtraction
    Multiplication
  */
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
        resultTextView.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        //Creating a new timer and hooking it up to timerTextView
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void chooseAnswer(View view) {

        //Getting the tag of the clicked button. and converting it toString
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("CORRECT!");
            score++;
        } else {
            resultTextView.setText("WRONG! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void newQuestion() {
        //Creating two random numbers between 0 and 20 for sumTextView
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));


        locationOfCorrectAnswer = rand.nextInt(4);


        answers.clear();

        for (int i=0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooking up variables to the view.
        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);






    }
}
