package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myViewHolder>{
    ArrayList<User> usersList;
    AlertDialog.Builder builder;
    TextView nametxt;
    TextView destxt;
    TextView txt;
    Context context;
    ArrayList<User>userList;

    public myAdapter(ArrayList<User> usersList) {


        this.usersList = usersList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0)
        {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.bigimage,parent,false);
            return new myViewHolder(item);
        }
        else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,
                    parent,
                    false);
            return new myViewHolder(item);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        String name = usersList.get(position).getName();
        String desc = usersList.get(position).getDescription();
        holder.nametxt.setText(name);
        holder.destxt.setText(desc);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertBox(position,v);
            }
        });


    }



    @Override
    public int getItemCount()
    {
        return usersList.size();
    }
    @Override
    public int getItemViewType(int position)
    {
        if (usersList.get(position).getName().endsWith("7"))
        {
            return 0;
        }
        else return 1;
    }
    private void openAlertBox(int pos,View v) {


        builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("User profile");
        builder.setMessage(usersList.get(pos).getName());
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Intent newAct = new Intent(v.getContext(),MainActivity.class);
                newAct.putExtra("username",usersList.get(pos).getName());
                newAct.putExtra("id",usersList.get(pos).getId());
                newAct.putExtra("desc",usersList.get(pos).getDescription());
                newAct.putExtra("followed",usersList.get(pos).getFollowed());
                v.getContext().startActivity(newAct);


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
}