package com.example.channelprotest_mvc.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.ActivityRewardsBinding;

public class RewardsActivity extends AppCompatActivity {

    ActivityRewardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding= DataBindingUtil.setContentView(this,R.layout.activity_rewards);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Rewards");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}