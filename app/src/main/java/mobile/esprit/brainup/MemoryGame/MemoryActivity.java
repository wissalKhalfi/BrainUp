package mobile.esprit.brainup.MemoryGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobile.esprit.brainup.R;

public class MemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(MemoryActivity.this, FirstMemoryActivity.class);
        startActivity(main);
    }
}
