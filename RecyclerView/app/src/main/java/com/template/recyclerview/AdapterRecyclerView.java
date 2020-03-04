package com.template.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder> {
    public static String TAG = "AdapterRecyclerView";

    Context context;
    ArrayList<User> userItems;

    public AdapterRecyclerView(Context context, ArrayList<User> userItems) {
        this.context = context;
        this.userItems = userItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        User user = userItems.get(position);

        holder.tvName.setText(user.getFirstName());
        holder.tvSurname.setText(user.getSurname());
        holder.tvStatus.setText(user.getStatusMessage());
        holder.ivProfilePic.setImageResource(user.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(context, "onClick: Clicked the whole view:  "+position, Toast.LENGTH_SHORT).show();

            }
        });

        holder.ivProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(context, "onClick: Clicked the picture:  "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvSurname, tvStatus;
        ImageView ivProfilePic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ivProfilePic = itemView.findViewById(R.id.ivProfile);
        }
    }

}
