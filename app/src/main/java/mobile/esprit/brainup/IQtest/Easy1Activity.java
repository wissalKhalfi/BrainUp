package mobile.esprit.brainup.IQtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mobile.esprit.brainup.R;

public class Easy1Activity extends AppCompatActivity {

    QuizHelper db;
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    int iqlevel;
    CounterClass timer;
    Question currentQ;
    @InjectView(R.id.txtQuestion) TextView _txtQuestion;
    @InjectView(R.id.score) TextView _score;
    @InjectView(R.id.timers) TextView _timers;
    @InjectView(R.id.IQchoice1) Button _button1;
    @InjectView(R.id.IQchoice2) Button _button2;
    @InjectView(R.id.IQchoice3) Button _button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy1);
        ButterKnife.inject(this);
        Bundle b = getIntent().getExtras();
        iqlevel = b.getInt("Iqlevel");
        db = new QuizHelper(this);
        switch (iqlevel){
            case 1:
                quesList = db.getAllQuestions();
                break;
            case 2:
                quesList=db.getAllQuestionsLevel2();
                break;
            case 3:
                quesList=db.getAllQuestionsLevel3();
                break;
            case 4:
                quesList=db.getAllQuestionsLevel4();
                break;
        }

        currentQ = quesList.get(qid);
        setQuestionView();
        _timers.setText("00:02:00");
        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        timer = new CounterClass(60000, 1000);
        timer.start();

        _button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(_button1.getText().toString());

            }
        });

        _button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(_button2.getText().toString());

            }
        });
        _button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(_button3.getText().toString());

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(Easy1Activity.this, FirstActivity.class);
        startActivity(main);
        timer.cancel();
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getAnswer().equals(AnswerString)) {
            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score++;
            _score.setText("Score : " + score);

        } else {
            // if unlucky start result activity and finish the game
            Toast.makeText(Easy1Activity.this, "Sorry, wrong answer", Toast.LENGTH_SHORT).show();
            timer.cancel();
            timer.onFinish();
            //Intent intent= new Intent(Easy1Activity.this, ResultActivity.class);
            // passing the int value
            /*Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();*/
        }
        //Toast.makeText(Easy1Activity.this, "qid=" +qid, Toast.LENGTH_SHORT).show();

        if (qid < 10 ) {
            // if questions are not over then do this
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            finishlevel();
        }

        }
    private void finishlevel(){
        // if over do this

        timer.cancel();
        timer.onFinish();

        /*Intent intent = new Intent(Easy1Activity.this, ResultActivity.class);
        Bundle bun = new Bundle();
        bun.putInt("score", score); // Your score
        intent.putExtras(bun); // Put your score to your next
        startActivity(intent);
        finish();*/
    }
    private void setQuestionView() {

        // the method which will put all things together
        _txtQuestion.setText(currentQ.getQUESTION());
        _button1.setText(currentQ.getOptA());
        _button2.setText(currentQ.getOptB());
        _button3.setText(currentQ.getOptC());
        qid++;
    }

    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format( "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            _timers.setText(hms);



        }

        @Override
        public void onFinish() {
            _timers.setText("Time is up");
            Toast.makeText(Easy1Activity.this, "Time is up", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Easy1Activity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();

        }
    }


}


