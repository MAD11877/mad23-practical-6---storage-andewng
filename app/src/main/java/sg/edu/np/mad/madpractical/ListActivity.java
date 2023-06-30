package sg.edu.np.mad.madpractical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    ArrayList<User> usersList= new ArrayList<>();
    ArrayList<User> usersList1= new ArrayList<>();

    private RecyclerViewClickListener listener;

    String title ="List Activity";
    ImageView imageView;
    AlertDialog.Builder builder;
    TextView txt;
    MyDbHandler db= new MyDbHandler(this,"UserDb.db",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(title,"On create");












    }
    private int generateNO(){
        Random random= new Random();
        int randNum =random.nextInt(99999999);
        return randNum;

    }
    Integer RandomNum;

    private void openAlertBox(int pos) {
        txt = findViewById(R.id.textView);
        builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("User profile");
        builder.setMessage(usersList1.get(pos).getName()+generateNO());
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                RandomNum=generateNO();
                Intent newAct = new Intent(ListActivity.this,MainActivity.class);
                newAct.putExtra("randomNumber",RandomNum);
                newAct.putExtra("username",usersList1.get(pos).getName());
                newAct.putExtra("desc",usersList1.get(pos).getDescription());
                newAct.putExtra("followed",usersList1.get(pos).getFollowed());
                startActivity(newAct);


            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.v(title,"On resume");
        CreateUser();

        Log.v(title,"Create user");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        setOnClickListener();
        myAdapter mAdapter = new myAdapter(usersList1);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



    }

    private void setOnClickListener(){
        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int pos) {
                openAlertBox(pos);
            }
        };
    }
    private void CreateUser()
    {

        int i=0;
        Random random=new Random();
        for (i=0;i<=20;i++)
        {
            User user = new User("Name"+generateNO(),"Description"+generateNO(),generateNO(),random.nextBoolean());
            db.addUsers(user);

        }
        readData();


    }
    public void readData()
    {
        usersList1=db.getUser();


    }



    public interface RecyclerViewClickListener{
        void onClick(View v,int pos);

    }








}