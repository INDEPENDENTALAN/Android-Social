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
import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SignInFragment extends Fragment {

    EditText editText_sign_in_username, editText_sign_in_password;
    Button button_sign_in_sign_up, button_sign_in;
    FirebaseAuth firebaseAuth;

    public SignInFragment() {
        //
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_sign_in_username = view.findViewById(R.id.editText_sign_in_username);
        editText_sign_in_username = view.findViewById(R.id.editText_sign_in_password);
        button_sign_in_sign_up = view.findViewById(R.id.button_sign_in_sign_up);
        button_sign_in = view.findViewById(R.id.button_sign_in);
        firebaseAuth = FirebaseAuth.getInstance();
        button_sign_in_sign_up.setOnClickListener(view1 -> {
            //
            startActivityForResult(new Intent(getActivity(), FragmentActivity.class).putExtra("request", 1), 1);
        });
        button_sign_in.setOnClickListener(view1 -> {
            //
            //if (!editText_sign_in_username.getText().toString().isEmpty() && !editText_sign_in_password.getText().toString().isEmpty()) {
                //
                firebaseAuth.createUserWithEmailAndPassword(editText_sign_in_username.getText().toString() + "@outlook.com", editText_sign_in_password.getText().toString()).addOnCompleteListener(task -> {
                    //
                    if (task.isSuccessful()) {
                        //
                        getActivity().getSharedPreferences("auth", MODE_PRIVATE).edit().putString("username", editText_sign_in_username.getText().toString()).putInt("sign", 0).apply();
                        getActivity().setResult(RESULT_OK);
                        getActivity().finish();
                    } else {
                        //
                    }
                });
            //}
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //
            getActivity().setResult(RESULT_OK);
            getActivity().finish();
        }
    }
}