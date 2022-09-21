package com.example.channelprotest_mvc.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.channelprotest_mvc.Controller.SessionManager;
import com.example.channelprotest_mvc.Model.User;
import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.ActivityRegisterBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    Spinner spinner;
    Toolbar toolbar;
    String code,token;
    String[] cities= {"google","FaceBook","Instagram","Meta","Whastapp",
            "Test","Company","School"};
    PopupMenu popupMenu;
    List<String> resultCities ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        LinkText();
         toolbar =binding.toolbar;
        setSupportActionBar(toolbar);



        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        spinner = binding.spinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.countryCodes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Search();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

              code=  spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstEdit.getText().toString();
                String lastName = binding.lastEdit.getText().toString();
                String mobileName = binding.numberEdit.getText().toString();
                String emailName = binding.emailEdit.getText().toString();
                String passwordName = binding.passwordEdit.getText().toString();
                String companyName = binding.companyEdit.getText().toString();
                if (TextUtils.isEmpty(firstName)){

                    binding.firstEdit.setError("Enter your first name");
                    binding.firstEdit.requestFocus();

                }
                if (TextUtils.isEmpty(lastName)){
                    binding.lastEdit.setError("Enter your last name");
                    binding.lastEdit.requestFocus();

                }

                if (TextUtils.isEmpty(mobileName)){

                    binding.numberEdit.setError("Enter your mobile number");
                    binding.numberEdit.requestFocus();
                }
                if (TextUtils.isEmpty(emailName)){

                    binding.emailEdit.setError("Enter your email");
                    binding.emailEdit.requestFocus();
                }
                if (TextUtils.isEmpty(passwordName)){

                    binding.passwordEdit.setError("Enter your password");
                    binding.passwordEdit.requestFocus();
                }
                if (TextUtils.isEmpty(companyName)){

                    binding.companyEdit.setError("Enter your company name");
                    binding.companyEdit.requestFocus();
                }

                if (binding.checkboxX.isChecked())
                {

                    if ((!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(mobileName) && !TextUtils.isEmpty(emailName) && !TextUtils.isEmpty(passwordName) && !TextUtils.isEmpty(companyName)))
                    {
                        User user = new User(firstName.trim(),lastName.trim(),spinner.getSelectedItem().toString()+mobileName,emailName,passwordName,companyName,"ihikjgyglu7tyfvkv");

                        SessionManager.getInstance(getApplicationContext()).userLogin(user);

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please Accept the Terms and Conditions and Privacy Policy!", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void LinkText(){

        //for the note text
        String text = "if your company is not registred and or you have an issue registering please send us a note Click here";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                composeEmail("pr.alihaydar@gmail.com","");
            }
        };



        ss.setSpan(clickableSpan,92,102,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.firstMarked.setText(ss);
        binding.firstMarked.setMovementMethod(LinkMovementMethod.getInstance());

        //for the checkbox text
        String text_1 = "I accept the Terms and Conditions and Privacy Policy";

        SpannableString ss_1 = new SpannableString(text_1);

        ClickableSpan clickableSpan_1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        };

        ClickableSpan clickableSpan_2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        };



        ss_1.setSpan(clickableSpan_1,13,33,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_1.setSpan(clickableSpan_2,38,52,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.txtCheck.setText(ss_1);
        binding.txtCheck.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void Popup(List<String> cities) {

         popupMenu = new PopupMenu(this, binding.companyEdit);

        //add menu items in popup menu

         if(cities != null) {

            for (int i = 0; i < cities.size(); i++) {

                 popupMenu.getMenu().add(Menu.NONE, i, i, cities.get(i));

            }

            //handle menu item clicks
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    //get id of the clicked item
                    //handle clicks

                    String choose = (String) menuItem.getTitle();

                    binding.companyEdit.setText(choose);

                    return false;
                }
            });


        }

    }

    public void Search(){


        binding.companyEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.companyEdit.getText().toString().equals("")){
                 resultCities = search(String.valueOf(s));
                Popup(resultCities);
                popupMenu.show();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });



    }
    public List<String> search(String txtsearch) {
        List<String> arr = new ArrayList<>();
        int pos=0;
        for (int i = 0; i < cities.length; i++) {

            if(cities[i].contains(txtsearch)){

                arr.add(pos, cities[pos]);
                pos++;
            }
        }
        return arr;
    }


}