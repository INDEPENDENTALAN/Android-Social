package com.android.social.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.social.R;
import com.android.social.adapter.ActivityAdapter;
import com.android.social.adapter.HomeAdapter;
import com.android.social.entity.ActivityEntity;
import com.android.social.entity.PostEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ActivityFragment extends Fragment {

    RecyclerView recyclerView_activity;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ActivityEntity> activityEntityArrayList;
    ActivityAdapter activityAdapter;
    FirebaseFirestore firebaseFirestore;
    public ActivityFragment() {
        //
    }

    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        //
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_activity = view.findViewById(R.id.recyclerView_activity);
        recyclerView_activity.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_activity.setLayoutManager(layoutManager);
        activityEntityArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Activity").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //
                if (task.isSuccessful()) {
                    //
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        //
                        activityEntityArrayList.add(queryDocumentSnapshot.toObject(ActivityEntity.class));
                    }
                    activityAdapter = new ActivityAdapter(activityEntityArrayList, getActivity(), firebaseFirestore);
                    recyclerView_activity.setAdapter(activityAdapter);
                } else {
                    //
                }
            }
        });
    }
}