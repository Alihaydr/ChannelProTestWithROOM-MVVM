package com.example.channelprotest_mvc.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        LinkText();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));


            }
        });

    }
    public void LinkText(){

        //for the note text
        String text = "Not registered yet? Create an Account";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));


            }
        };
        String text_1 = "Forgot Password !";

        SpannableString ss_2 = new SpannableString(text_1);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));


            }
        };



        ss.setSpan(clickableSpan,20,37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_2.setSpan(clickableSpan1,0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.transferAction.setText(ss);
        binding.transferAction.setMovementMethod(LinkMovementMethod.getInstance());
        binding.forgot.setText(ss_2);
        binding.forgot.setMovementMethod(LinkMovementMethod.getInstance());



    }
}