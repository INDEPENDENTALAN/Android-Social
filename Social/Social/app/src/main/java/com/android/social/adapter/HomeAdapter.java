package com.android.social.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.social.R;
import com.android.social.entity.PostEntity;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private ArrayList<PostEntity> postEntityArrayList;

    public HomeAdapter(ArrayList<PostEntity> postEntityArrayList) {
        this.postEntityArrayList = postEntityArrayList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.textView_home_username.setText(postEntityArrayList.get(position).getUsername());
        holder.textView_home_post.setText(postEntityArrayList.get(position).getPost());
        holder.textView_home_likes.setText(postEntityArrayList.get(position).getLikes());
        holder.imageButton_home_like.setOnClickListener(view -> {
            //
        });
    }

    @Override
    public int getItemCount() {
        return postEntityArrayList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView textView_home_username, textView_home_post, textView_home_likes;
        ImageButton imageButton_home_like;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_home_username = itemView.findViewById(R.id.textView_home_username);
            textView_home_post = itemView.findViewById(R.id.textView_home_post);
            textView_home_likes = itemView.findViewById(R.id.textView_home_likes);
            imageButton_home_like = itemView.findViewById(R.id.imageButton_home_like);
        }
    }
}
