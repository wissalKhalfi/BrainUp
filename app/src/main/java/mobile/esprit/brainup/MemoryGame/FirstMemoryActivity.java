package mobile.esprit.brainup.MemoryGame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import mobile.esprit.brainup.CustomGrid;
import mobile.esprit.brainup.GameMenuActivity;
import mobile.esprit.brainup.R;

public class FirstMemoryActivity extends Activity {
    Intent main;
    SharedPreferences sharedpreferences;
    GridView grid;
    CustomGrid adapter;
    String[] AppName = {"level 1", "level 2", "level 3", "level 4"} ;
    int[] Logoid = {R.drawable.level, R.drawable.lock, R.drawable.lock, R.drawable.lock};
    int[] Logoid1 = {R.drawable.level, R.drawable.level, R.drawable.lock, R.drawable.lock};
    int[] Logoid2 = {R.drawable.level, R.drawable.level, R.drawable.level, R.drawable.lock};
    int[] Logoid3 = {R.drawable.level, R.drawable.level, R.drawable.level, R.drawable.level};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_memory);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final int next=sharedpreferences.getInt("Memorylevelnext",1);
        Log.d("Level ", "Next: "+next);
        if(next != 0){
            switch (next){

                case 1:
                    adapter = new CustomGrid( AppName, Logoid, FirstMemoryActivity.this);

                    break;
                case 2:
                    adapter = new CustomGrid( AppName, Logoid1, FirstMemoryActivity.this);
                    break;
                case 3:
                    adapter = new CustomGrid( AppName, Logoid2, FirstMemoryActivity.this);
                    break;
                case 4:
                    adapter = new CustomGrid( AppName, Logoid3, FirstMemoryActivity.this);
                    break;
            }
        }
        grid=(GridView)findViewById(R.id.grid_Menumemory);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (next){

                    case 1:
                        if (position == 0)
                            LevelLaunch(position);
                        break;
                    case 2:
                        if (position == 0 || position == 1)
                            LevelLaunch(position);
                        break;
                    case 3:
                        if (position == 0 || position == 1 || position == 2)
                            LevelLaunch(position);
                        break;
                    case 4:
                        if (position == 0 || position == 1 || position == 2 || position == 3)
                            LevelLaunch(position);
                        break;
                }

                Toast.makeText(FirstMemoryActivity.this, "You Clicked at: "+position +AppName[+ position], Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void LevelLaunch(int position){
        Bundle b = new Bundle();
        b.putInt("memorylevel", position+1);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("memorylevelplaying", position+1);
        editor.commit();
        main = new Intent(FirstMemoryActivity.this, MemoryActivity.class);
        main.putExtras(b);
        startActivity(main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        main = new Intent(FirstMemoryActivity.this, GameMenuActivity.class);
        startActivity(main);
    }
}
