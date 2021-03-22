package com.android.social.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.social.R;
import com.android.social.entity.AccountEntity;
import com.android.social.entity.ActivityEntity;
import com.android.social.entity.PostEntity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<AccountEntity> accountEntityArrayList;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore;

    public SearchAdapter(ArrayList<AccountEntity> accountEntityArrayList, Activity activity, FirebaseFirestore firebaseFirestore) {
        this.accountEntityArrayList = accountEntityArrayList;
        this.activity = activity;
        this.firebaseFirestore = firebaseFirestore;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.textView_search_username.setText(accountEntityArrayList.get(position).getUsername());
        holder.button_search_follow.setOnClickListener(view -> {
            //
            firebaseFirestore.collection("Activity").add(new ActivityEntity(activity.getSharedPreferences("auth", MODE_PRIVATE).getString("username", ""), accountEntityArrayList.get(position).getUsername(), 0)).addOnSuccessListener(documentReference -> {
                //
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                //
            });
        });
    }

    @Override
    public int getItemCount() {
        return accountEntityArrayList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView textView_search_username;
        Button button_search_follow;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_search_username = itemView.findViewById(R.id.textView_search_username);
            button_search_follow = itemView.findViewById(R.id.button_search_follow);
        }
    }

    public void onSearch(ArrayList<AccountEntity> accountEntityArrayList){
        this.accountEntityArrayList = accountEntityArrayList;
        notifyDataSetChanged();
    }
}
