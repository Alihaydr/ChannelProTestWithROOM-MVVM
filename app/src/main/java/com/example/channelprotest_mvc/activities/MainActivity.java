package com.example.channelprotest_mvc.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.channelprotest_mvc.Controller.SessionManager;
import com.example.channelprotest_mvc.Model.User;
import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.ActivityMainBinding;
import com.example.channelprotest_mvc.databinding.NavHeaderTestBinding;
import com.example.channelprotest_mvc.fragments.FeedbackFragment;
import com.example.channelprotest_mvc.fragments.HomeFragment;
import com.example.channelprotest_mvc.fragments.InboxFragment;
import com.example.channelprotest_mvc.fragments.NewsFragment;
import com.example.channelprotest_mvc.fragments.ReportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

     public ActionBarDrawerToggle actionBarDrawerToggle;
    public ActionBar actionBar;
    public ActivityMainBinding activityMainBinding;
    NavHeaderTestBinding nav_header_main;
    String token;
    public static WeakReference<MainActivity> weakActivity;
    User user;
    public static MainActivity getmInstanceActivity() {
        return weakActivity.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

        weakActivity = new WeakReference<>(MainActivity.this);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         nav_header_main = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_test, activityMainBinding
                 .navView, false);
         activityMainBinding.navView.addHeaderView(nav_header_main.getRoot());
/*

         nav_header_main.logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                SessionManager.getInstance(MainActivity.this).userLogout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();

             }
         });
*/

         actionBar = getSupportActionBar();
         if (SessionManager.getInstance(this).isLoggedIn()) {

             if (SessionManager.getInstance(this).getToken() != null) {

                 String Name, email;
                 Name = SessionManager.getInstance(this).getUserInfo().getFirst_name();
                 email = SessionManager.getInstance(this).getUserInfo().getEmail();
                 token = SessionManager.getInstance(this).getToken().getToken();
                 Log.d("name : ", Name + "");
                 Log.d("email : ", email + "");

                 nav_header_main.name.setText(Name);
                 nav_header_main.email.setText(email);
             } else {

                 finish();
                 startActivity(new Intent(this, LoginActivity.class));
                 return;
             }

         }


         // Set BackgroundDrawable

         setSupportActionBar(activityMainBinding.toolbar);
         activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
         activityMainBinding.bottomNavigationView.setSelectedItemId(R.id.home);

         actionBarDrawerToggle = new ActionBarDrawerToggle(this, activityMainBinding.drawerLayout, R.string.nav_open, R.string.nav_close);

         actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         // pass the Open and Close toggle for the drawer layout listener
         // to toggle the button
         activityMainBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
         actionBarDrawerToggle.syncState();

         activityMainBinding.navView.setNavigationItemSelectedListener(item -> {
             if (item.getItemId() == R.id.logout_) {
                 SessionManager.getInstance(MainActivity.this).userLogout();
                 startActivity(new Intent(MainActivity.this, LoginActivity.class));
                 finish();
             }
             return true;

         });
     }
    FeedbackFragment feedbackFragment = new FeedbackFragment();
    HomeFragment homeFragment = new HomeFragment();
    InboxFragment inboxFragment = new InboxFragment();
    NewsFragment newsFragment = new NewsFragment();
    ReportFragment reportFragment = new ReportFragment();
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            if (item.getItemId()==R.id.logout_)
            {
                SessionManager.getInstance(MainActivity.this).userLogout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                activityMainBinding.appBarMain.setVisibility(View.VISIBLE);
                return true;

            case R.id.feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, feedbackFragment).commit();
                activityMainBinding.appBarMain.setVisibility(View.GONE);
                return true;

            case R.id.report:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, reportFragment).commit();
                activityMainBinding.appBarMain.setVisibility(View.GONE);
                return true;
            case R.id.news:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, newsFragment).commit();
                activityMainBinding.appBarMain.setVisibility(View.GONE);
                return true;
            case R.id.inbox:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, inboxFragment).commit();
                activityMainBinding.appBarMain.setVisibility(View.GONE);
                return true;

        }
      /*  if (item.getItemId()==R.id.logout_)
        {
            SessionManager.getInstance(MainActivity.this).userLogout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }*/
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof TextInputEditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }


}