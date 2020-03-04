package com.template.users;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewUsers extends RecyclerView.Adapter<AdapterRecyclerViewUsers.ViewHolderUser>{

    Context context;
    List<User> listUsers = new ArrayList<>();


    ViewModelUsers viewModelUsers;

    public AdapterRecyclerViewUsers(Context context) {
        this.context = context;
        viewModelUsers = ViewModelProviders.of((MainActivity) context).get(ViewModelUsers.class);
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_user, parent, false);

        ViewHolderUser viewHolder = new ViewHolderUser(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderUser holder, final int position) {
        User user = listUsers.get(position);

        holder.tvName.setText(user.getName());
        holder.tvSurname.setText(user.getSurname());
        holder.tvBio.setText(user.getBio());
        holder.ivProPic.setImageResource(user.getProPic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(context, "onClick: Clicked the whole view:  "+position, Toast.LENGTH_SHORT).show();

                viewModelUsers.delete(listUsers.get(position));
            }
        });

        holder.ivProPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(context, "onClick: Clicked the picture:  "+position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewModelUsers.update(listUsers.get(position));
                Toast.makeText(context, "UPDATED!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }


    public void setUsers(List<User> users){
        this.listUsers = users;
        notifyDataSetChanged();
    }


    public class ViewHolderUser extends RecyclerView.ViewHolder{

        TextView tvName, tvSurname, tvBio;
        ImageView ivProPic;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            tvBio = itemView.findViewById(R.id.tvBio);

            ivProPic = itemView.findViewById(R.id.ivProfile);
        }

    }       //end innerClass

}       //end class
