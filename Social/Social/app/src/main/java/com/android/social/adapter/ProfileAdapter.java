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

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<PostEntity> postEntityArrayList;

    public ProfileAdapter(ArrayList<PostEntity> postEntityArrayList) {
        this.postEntityArrayList = postEntityArrayList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.textView_profile_post.setText(postEntityArrayList.get(position).getPost());
        holder.textView_profile_likes.setText(postEntityArrayList.get(position).getLikes());
        holder.imageButton_profile_like.setOnClickListener(view -> {
            //
        });
    }

    @Override
    public int getItemCount() {
        return postEntityArrayList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textView_profile_post, textView_profile_likes;
        ImageButton imageButton_profile_like;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_profile_post = itemView.findViewById(R.id.textView_profile_post);
            textView_profile_likes = itemView.findViewById(R.id.textView_profile_likes);
            imageButton_profile_like = itemView.findViewById(R.id.imageButton_profile_like);
        }
    }
}
