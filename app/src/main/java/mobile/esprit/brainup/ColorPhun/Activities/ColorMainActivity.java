package mobile.esprit.brainup.ColorPhun.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mobile.esprit.brainup.ConcentrationAppMenuActivity;
import mobile.esprit.brainup.R;

public class ColorMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_main);

    }
    public void playGame(View view) {
        startActivity(new Intent(this, EasyGameColorPhunActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ColorMainActivity.this, ConcentrationAppMenuActivity.class));
    }
}
