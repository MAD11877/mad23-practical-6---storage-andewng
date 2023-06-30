package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MessageGroup extends AppCompatActivity {
    String title ="MessageGroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Log.v(title,"On create");

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(title,"On start");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(title,"On pause:");

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.v(title,"On resume");


        Button Group1 = findViewById(R.id.button3);
        Group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(title,"Group 1 clicked");
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.foofragment,new Fragment1());
                ft.commit();
            }

        });







        Button Group2 = findViewById(R.id.button4);
        Group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(title,"Group 2 clicked");
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.foofragment,new Fragment2());
                ft.commit();

            }
        });

    }
}