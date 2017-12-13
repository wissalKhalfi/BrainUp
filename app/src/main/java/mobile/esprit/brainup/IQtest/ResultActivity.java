package mobile.esprit.brainup.IQtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mobile.esprit.brainup.GameMenuActivity;
import mobile.esprit.brainup.R;

public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.textResultIQ) TextView _IQresultText;
    @InjectView(R.id.btn_replay_iq) Button _BtnreplayIQ;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        _IQresultText.setText("Your score is " + " " + score +". Thanks for playing my game.");
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putInt("ScoreIqTest", score);
        editor.commit();
        Docheck(score);
        _BtnreplayIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replay = new Intent(ResultActivity.this, FirstActivity.class);
                startActivity(replay);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent replay = new Intent(ResultActivity.this, GameMenuActivity.class);
        startActivity(replay);
    }
    public void Docheck(int score){
        if(score>5){
            editor=sharedpreferences.edit();
            int current_level=sharedpreferences.getInt("IQlevelplaying", 1);
            switch (current_level){

                case 1:
                    editor.putInt("IQlevelnext",2);
                    break;
                case 2:
                    editor.putInt("IQlevelnext",3);
                    break;
                case 3:
                    editor.putInt("IQlevelnext",4);
                    break;
                case 4:
                    editor.putInt("IQlevelnext",4);
                    break;
                default:
                    editor.putInt("IQlevelnext",4);
                    break;
            }
        editor.commit();
        }
    }
}
