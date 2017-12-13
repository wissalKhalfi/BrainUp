package mobile.esprit.brainup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mobile.esprit.brainup.MemoryGame.FirstMemoryActivity;

public class GameMenuActivity extends AppCompatActivity {

    @InjectView(R.id.btn_concentation) Button _concentrationButton;
    @InjectView(R.id.btn_intelligence) Button _intelligenceButton;
    @InjectView(R.id.btn_memory) Button _memoryButton;
    @InjectView(R.id.btn_profile) Button _profileAnalysisButton;
    Intent main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        ButterKnife.inject(this);
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        _concentrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playConcentration();
                v.startAnimation(animAlpha);
            }
        });
        _intelligenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playIntelligence();
                v.startAnimation(animAlpha);
            }
        });
        _memoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMemory();
                v.startAnimation(animAlpha);
            }
        });
        _profileAnalysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAnalysis();
                v.startAnimation(animAlpha);
            }
        });
    }
    public void playConcentration(){

         main = new Intent(GameMenuActivity.this, ConcentrationAppMenuActivity.class);
        startActivity(main);
    }
    public void playIntelligence(){
        main = new Intent(GameMenuActivity.this, IntelligenceAppMenuActivity.class);
        startActivity(main);
    }
    public void playMemory(){
        main = new Intent(GameMenuActivity.this, FirstMemoryActivity.class);
        startActivity(main);
    }
    public void playAnalysis(){
        main = new Intent(GameMenuActivity.this, PlayerProfileActivity.class);
        startActivity(main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

}
