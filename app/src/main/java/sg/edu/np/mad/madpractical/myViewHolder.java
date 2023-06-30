package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView nametxt;
    TextView destxt;
    ImageView imageView,imageView2;
    private ListActivity.RecyclerViewClickListener listener;

    public myViewHolder(View itemView){
        super(itemView);
        nametxt=itemView.findViewById(R.id.textView3);
        destxt=itemView.findViewById(R.id.textView4);
        imageView = itemView.findViewById(R.id.imageView2);
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View itemView) {
        listener.onClick(itemView,getAdapterPosition());

    }



}