package com.chess254.archcomp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chess254.archcomp.Models.User;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by chess on 10/23/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter <UserListAdapter.UserViewHolder>{

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView phone;
        private final TextView email;
        private final TextView address;
        private final TextView image;
        private final TextView name;
//        private final TextView userItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
//           userItemView = itemView.findViewById(R.id.textView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
            image = itemView.findViewById(R.id.image);


        }
    }

    private final LayoutInflater mInflater;

    private List<User> mUsers;

//        //added to test firebase
//    private ArrayList mUsers = new ArrayList<User>();

    UserListAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.UserViewHolder holder, int position) {
        if (mUsers != null) {
           User current = mUsers.get(position);
            holder.name.setText(current.getUserName());
            holder.phone.setText(current.getUserPhone());
            holder.email.setText(current.getUserEmail());
            holder.address.setText(current.getUserAddress());
            holder.image.setText(current.getUserImage());

        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No data");
        }
    }


    void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }



    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }
}
