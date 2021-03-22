package com.android.social.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.social.R;
import com.android.social.entity.AccountEntity;
import com.android.social.entity.PostEntity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class PostFragment extends Fragment {

    EditText editText_post_post;
    Button button_post_post;
    FirebaseFirestore firebaseFirestore;

    public PostFragment() {
        //
    }

    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();
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
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_post_post = view.findViewById(R.id.editText_post_post);
        button_post_post = view.findViewById(R.id.button_post_post);
        Random random = new Random();
        int likes = random.nextInt(900);
        firebaseFirestore = FirebaseFirestore.getInstance();
        button_post_post.setOnClickListener(view1 -> {
            //
            firebaseFirestore.collection("Post").add(new PostEntity(getActivity().getSharedPreferences("auth", MODE_PRIVATE).getString("username", ""), editText_post_post.getText().toString(), String.valueOf(likes))).addOnSuccessListener(documentReference -> {
                //
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                //
            });
        });
    }
}