package com.android.social.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.social.R;
import com.android.social.activity.FragmentActivity;
import com.android.social.activity.SocialActivity;
import com.android.social.adapter.SearchAdapter;
import com.android.social.entity.AccountEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SignUpFragment extends Fragment {

    EditText editText_sign_up_username, editText_sign_up_password, editText_sign_up_confirm;
    Button button_sign_up_sign_in, button_sign_up;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public SignUpFragment() {
        //
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_sign_up_username = view.findViewById(R.id.editText_sign_up_username);
        editText_sign_up_password = view.findViewById(R.id.editText_sign_up_password);
        editText_sign_up_confirm = view.findViewById(R.id.editText_sign_up_confirm);
        button_sign_up_sign_in = view.findViewById(R.id.button_sign_up_sign_in);
        button_sign_up = view.findViewById(R.id.button_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        button_sign_up_sign_in.setOnClickListener(view1 -> {
            //
            getActivity().setResult(RESULT_CANCELED);
            getActivity().finish();
        });
        firebaseFirestore = FirebaseFirestore.getInstance();
        button_sign_up.setOnClickListener(view1 -> {
            //
            //if (!editText_sign_up_username.getText().toString().isEmpty() && !editText_sign_up_password.getText().toString().isEmpty() && !editText_sign_up_confirm.getText().toString().isEmpty()) {
                //
                //if (editText_sign_up_password.getText().toString().equals(editText_sign_up_confirm.getText().toString())) {
                    //
                    firebaseAuth.signInWithEmailAndPassword(editText_sign_up_username.getText().toString() + "@outlook.com", editText_sign_up_password.getText().toString()).addOnCompleteListener(task -> {
                        //
                        if (task.isSuccessful()) {
                            //
                            firebaseFirestore.collection("Account").add(new AccountEntity(editText_sign_up_username.getText().toString())).addOnSuccessListener(documentReference -> {
                                //
                                getActivity().getSharedPreferences("auth", MODE_PRIVATE).edit().putString("username", editText_sign_up_username.getText().toString()).putInt("sign", 0).apply();
                                getActivity().setResult(RESULT_OK);
                                getActivity().finish();
                            }).addOnFailureListener(e -> {
                                //
                            });
                        } else {
                            //
                        }
                    });
                //}
            //}
        });
    }
}