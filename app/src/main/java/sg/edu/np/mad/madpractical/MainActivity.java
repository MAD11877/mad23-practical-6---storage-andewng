package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final String TITLE= "Main Activity";
    User myUser;
    Button myButton;
    SharedPreferences sharedPreferences;
    MyDbHandler db;
    ArrayList<User>userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myGetIntent= getIntent();
        Integer myReceive = myGetIntent.getIntExtra("randomNumber",0);
        String MyName=myGetIntent.getStringExtra("username");
        String MyDesc=myGetIntent.getStringExtra("desc");
        Boolean MyFllw =myGetIntent.getExtras().getBoolean("followed");
        Integer MyID = myGetIntent.getExtras().getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE,"On create:");
        TextView txt = findViewById(R.id.textView);
        txt.setText(MyName+ myReceive);
        TextView txtt =findViewById(R.id.textView2);
        txtt.setText(MyDesc);

        myUser = new User(MyName,MyDesc,MyID,MyFllw);

        myButton=findViewById(R.id.button);
        if (myUser.followed)
        {
            myButton.setText("Unfollow");
        }
        else
        {
            myButton.setText("Follow");
        }

        db= new MyDbHandler(MainActivity.this,null,null,1);



    }



    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE,"On start:");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE,"On pause:");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TITLE, "On resume:");

        myButton=findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myUser.followed)
                {
                    myUser.followed = false;
                    myButton.setText("Follow");
                    Log.v(TITLE, "Follow");
                    Toast.makeText(MainActivity.this,"Unfollowed",Toast.LENGTH_SHORT).show();
                    db.updateUser(myUser);
                }
                else
                {
                    myUser.followed=true;
                    myButton.setText("Unfollow");
                    Log.v(TITLE, "Unfollow");
                    Toast.makeText(MainActivity.this,"Followed",Toast.LENGTH_SHORT).show();
                    db.updateUser(myUser);

                }






            }
        });
        Button myButton2 = findViewById(R.id.button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TITLE, "Description");
                Intent GoToMsg = new Intent(MainActivity.this,MessageGroup.class );
                startActivity(GoToMsg);



            }
        });



    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE, "On stop:");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE,"On destroy");
    }




}