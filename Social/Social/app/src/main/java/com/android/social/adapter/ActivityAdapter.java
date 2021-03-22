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
import com.android.social.entity.ActivityEntity;
import com.android.social.entity.PostEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private ArrayList<ActivityEntity> activityEntityArrayList;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore;

    public ActivityAdapter(ArrayList<ActivityEntity> activityEntityArrayList, Activity activity, FirebaseFirestore firebaseFirestore) {
        this.activityEntityArrayList = activityEntityArrayList;
        this.activity = activity;
        this.firebaseFirestore = firebaseFirestore;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_activity, parent, false));
    }

    int bool = 0;
    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        if (activity.getSharedPreferences("auth", MODE_PRIVATE).getString("username", "").equals(activityEntityArrayList.get(position).getUsername())) {
            holder.textView_activity_username.setText(activityEntityArrayList.get(position).getFollow_username());
            bool = 0;
        } else {
            holder.textView_activity_username.setText(activityEntityArrayList.get(position).getUsername());
            bool = 1;
        }
        if (activityEntityArrayList.get(position).getAccept_username() == 1) {
            holder.button_activity_accept.setTextColor(activity.getResources().getColor(R.color.colorIII));
            holder.button_activity_accept.setClickable(false);
        }
        holder.button_activity_accept.setOnClickListener(view -> {
            //
            if (bool == 0) {
                //
                firebaseFirestore.collection("Activity").whereEqualTo("username", activityEntityArrayList.get(position).getFollow_username()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<Object, Integer> map = new HashMap<>();
                                map.put("accept_username", 1);
                                firebaseFirestore.collection("Activity").document(document.getId()).set(map, SetOptions.merge());
                            }
                        }
                    }
                });
            } else {
                firebaseFirestore.collection("Activity").whereEqualTo("username", activityEntityArrayList.get(position).getUsername()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<Object, Integer> map = new HashMap<>();
                                map.put("accept_username", 1);
                                firebaseFirestore.collection("Activity").document(document.getId()).set(map, SetOptions.merge());
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityEntityArrayList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        TextView textView_activity_username;
        Button button_activity_accept;
        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_activity_username = itemView.findViewById(R.id.textView_activity_username);
            button_activity_accept = itemView.findViewById(R.id.button_activity_accept);
        }
    }
}
