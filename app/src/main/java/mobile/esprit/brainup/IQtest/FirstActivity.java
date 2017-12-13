package mobile.esprit.brainup.IQtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import mobile.esprit.brainup.CustomGrid;
import mobile.esprit.brainup.IntelligenceAppMenuActivity;
import mobile.esprit.brainup.R;

public class FirstActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_first);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final int next= sharedpreferences.getInt("IQlevelnext",1);
        if(next != 0){
            switch (next){

                case 1:
                    adapter = new CustomGrid( AppName, Logoid, FirstActivity.this);
                    break;
                case 2:
                    adapter = new CustomGrid( AppName, Logoid1, FirstActivity.this);
                    break;
                case 3:
                    adapter = new CustomGrid( AppName, Logoid2, FirstActivity.this);
                    break;
                case 4:
                    adapter = new CustomGrid( AppName, Logoid3, FirstActivity.this);
                    break;
            }
        }
        grid=(GridView)findViewById(R.id.grid_Menuconcentration);
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

                //LevelLaunch(position);
              //  Toast.makeText(FirstActivity.this, "You Clicked at: "+position +AppName[+ position], Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void LevelLaunch(int position){
        Bundle b = new Bundle();
        b.putInt("Iqlevel", position+1);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("IQlevelplaying", position+1);
        editor.commit();
        main = new Intent(FirstActivity.this, Easy1Activity.class);
        main.putExtras(b);
        startActivity(main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        main = new Intent(FirstActivity.this, IntelligenceAppMenuActivity.class);
        startActivity(main);
    }
}
