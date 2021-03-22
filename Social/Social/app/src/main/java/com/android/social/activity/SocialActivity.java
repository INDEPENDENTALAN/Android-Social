package com.android.social.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.social.R;
import com.android.social.fragment.ActivityFragment;
import com.android.social.fragment.HomeFragment;
import com.android.social.fragment.PostFragment;
import com.android.social.fragment.ProfileFragment;
import com.android.social.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SocialActivity extends AppCompatActivity {

    TextView textView_social;
    BottomNavigationView bottomNavigationView_social;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        if (getSharedPreferences("auth", MODE_PRIVATE).getInt("sign", -1) != 0) {
            startActivityForResult(new Intent(SocialActivity.this, FragmentActivity.class).putExtra("request", 0), 0);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new HomeFragment()).commit();
        textView_social = findViewById(R.id.textView_social);
        bottomNavigationView_social = findViewById(R.id.bottomNavigationView_social);
        bottomNavigationView_social.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                //
                case R.id.bottom_navigation_view_home: {
                    //
                    textView_social.setText("Home");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new HomeFragment()).commit();
                    return true;
                } case R.id.bottom_navigation_view_search: {
                    //
                    textView_social.setText("Search");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new SearchFragment()).commit();
                    return true;
                } case R.id.bottom_navigation_view_post: {
                    //
                    textView_social.setText("Post");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new PostFragment()).commit();
                    return true;
                } case R.id.bottom_navigation_view_activity: {
                    //
                    textView_social.setText("Activity");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new ActivityFragment()).commit();
                    return true;
                } case R.id.bottom_navigation_view_profile: {
                    //
                    textView_social.setText(getSharedPreferences("auth", MODE_PRIVATE).getString("username", ""));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_social, new ProfileFragment()).commit();
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            //
        }
    }
}