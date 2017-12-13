package mobile.esprit.brainup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mobile.esprit.brainup.ColorPhun.Activities.ColorMainActivity;
import mobile.esprit.brainup.SpaceShip.SpaceMainActivity;

public class ConcentrationAppMenuActivity extends AppCompatActivity {
    @InjectView(R.id.playcolorphun) Button _playColor;
    @InjectView(R.id.playspaceship) Button _playSpaceship;

    Intent main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_concentration_app_menu);
            ButterKnife.inject(this);


        _playSpaceship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSpaceship();
            }
        });
        _playColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playColorphun();
            }
        });
      }
    @Override
    public void onBackPressed() {
        Intent main = new Intent(ConcentrationAppMenuActivity.this, GameMenuActivity.class);
        startActivity(main);
    }
    public void playSpaceship(){
        main = new Intent(ConcentrationAppMenuActivity.this, SpaceMainActivity.class);
        startActivity(main);
    }

    public void playColorphun(){
        main = new Intent(ConcentrationAppMenuActivity.this, ColorMainActivity.class);
        startActivity(main);
    }
}
