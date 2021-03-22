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
import com.android.social.adapter.HomeAdapter;
import com.android.social.adapter.ProfileAdapter;
import com.android.social.adapter.SearchAdapter;
import com.android.social.entity.PostEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView_profile;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PostEntity> postEntityArrayList;
    ProfileAdapter profileAdapter;
    FirebaseFirestore firebaseFirestore;
    public ProfileFragment() {
        //
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_profile = view.findViewById(R.id.recyclerView_profile);
        recyclerView_profile.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_profile.setLayoutManager(layoutManager);
        postEntityArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Post").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //
                if (task.isSuccessful()) {
                    //
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        //
                        postEntityArrayList.add(queryDocumentSnapshot.toObject(PostEntity.class));
                    }
                    profileAdapter = new ProfileAdapter(postEntityArrayList);
                    recyclerView_profile.setAdapter(profileAdapter);
                } else {
                    //
                }
            }
        });
    }
}