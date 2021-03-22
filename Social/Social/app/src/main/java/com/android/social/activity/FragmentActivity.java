package com.android.social.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.social.R;
import com.android.social.fragment.HomeFragment;
import com.android.social.fragment.SignInFragment;
import com.android.social.fragment.SignUpFragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (getIntent().getIntExtra("request", -1) == 0) {
            //
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fragment, new SignInFragment()).commit();
        } else if (getIntent().getIntExtra("request", -1) == 1) {
            //
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fragment, new SignUpFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        //
    }
}