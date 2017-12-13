package mobile.esprit.brainup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.btn_exit) Button _exitButton;
    @InjectView(R.id.btn_play) Button _playButton;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);
        ref= FirebaseDatabase.getInstance().getReference();
        if (getIntent().getExtras() != null) {CheckUserData();}
        final Animation animRotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        _exitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                v.startAnimation(animRotate);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        _playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animRotate);
                play();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });

    }
    public void play(){
        Intent main = new Intent(MainActivity.this, GameMenuActivity.class);
        startActivity(main);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void CheckUserData(){
            String user_name = getIntent().getExtras().getString("user_nom").toString();
            String user_country= getIntent().getExtras().getString("user_country").toString();
            int user_age= getIntent().getExtras().getInt("user_age");
            UserInfo userInformations= new UserInfo(user_name,user_age,user_country);

            //initializing firebase authentication object
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            //if the user is not logged in that means current user will return null
            if (user == null) {
                Toast.makeText(this, "No user connected...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            DatabaseReference usersRef = ref.child("users").child(user.getUid());
            usersRef.setValue(userInformations);
            Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
            getIntent().getExtras().remove("user_nom");
            getIntent().getExtras().remove("user_country");
            getIntent().getExtras().remove("user_age");
    }


    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
