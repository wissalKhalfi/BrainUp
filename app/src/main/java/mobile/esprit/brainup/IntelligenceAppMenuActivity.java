package mobile.esprit.brainup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mobile.esprit.brainup.IQtest.FirstActivity;
import mobile.esprit.brainup.MathQuiz.MyActivity;

public class IntelligenceAppMenuActivity extends AppCompatActivity {

    @InjectView(R.id.playiq) Button _playiq;
    @InjectView(R.id.playmath) Button _playmath;
    Intent main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intelligence_app_menu);
        ButterKnife.inject(this);


        _playiq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = new Intent(IntelligenceAppMenuActivity.this, FirstActivity.class);
                startActivity(main);

            }
        });
        _playmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = new Intent(IntelligenceAppMenuActivity.this, MyActivity.class);
                startActivity(main);


            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent main = new Intent(IntelligenceAppMenuActivity.this, GameMenuActivity.class);
        startActivity(main);
    }
}
