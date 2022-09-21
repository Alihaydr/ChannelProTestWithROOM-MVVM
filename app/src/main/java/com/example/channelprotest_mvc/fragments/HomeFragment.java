package com.example.channelprotest_mvc.fragments;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Adapter;

import com.bumptech.glide.Glide;
import com.example.channelprotest_mvc.Controller.SessionManager;
import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.RoundedImg;
import com.example.channelprotest_mvc.ViewModel.HomeViewModel;
import com.example.channelprotest_mvc.ViewModel.NewsViewModel;
import com.example.channelprotest_mvc.ViewModel.PointsViewModel;
import com.example.channelprotest_mvc.activities.LoginActivity;
import com.example.channelprotest_mvc.activities.MainActivity;
import com.example.channelprotest_mvc.activities.PerformanceActivity;
import com.example.channelprotest_mvc.activities.PromotionsActivity;
import com.example.channelprotest_mvc.activities.RewardsActivity;
import com.example.channelprotest_mvc.activities.TrainingActivity;
import com.example.channelprotest_mvc.adapters.HomeAdapter;
import com.example.channelprotest_mvc.adapters.NewsAdapter;
import com.example.channelprotest_mvc.databinding.FragmentHomeBinding;
import com.example.channelprotest_mvc.room.Home;
import com.example.channelprotest_mvc.room.News;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {



    public HomeFragment() {


    }


    ArrayList<String> source;

    RecyclerView.LayoutManager RecyclerViewLayoutManager;


    LinearLayoutManager HorizontalLayout;

    View ChildView;
    int RecyclerViewItemPosition;

    private HomeViewModel homeViewModel;
    private PointsViewModel  pointsViewModel;
    FragmentHomeBinding binding;



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        OnClickAction();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.slackanimation);
        RoundedImg roundedImg = new RoundedImg(bm);
        binding.circle.setImageDrawable(roundedImg);

        if (SessionManager.getInstance(getActivity()).isLoggedIn()){

            if (SessionManager.getInstance(getActivity()).getToken() !=null){

                 String Name=SessionManager.getInstance(getActivity()).getUserInfo().getFirst_name();

                binding.bottom.setText("Hello! "+Name);

            }

        }


        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recylerview.setLayoutManager(HorizontalLayout);
        binding.recylerview.setHasFixedSize(true);

        HomeAdapter adapter = new HomeAdapter(getActivity());
        binding.recylerview.setAdapter(adapter);
        pointsViewModel = new ViewModelProvider(requireActivity()).get(PointsViewModel.class);

        binding.end.setText(pointsViewModel.getPoints()+" PT");

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getAllHome().observe(requireActivity(), new Observer<List<Home>>() {
            @Override
            public void onChanged(List<Home> homes) {

                if (homes != null) {
                    adapter.setHomes(homes);
                }

            }
        });

        return view;
    }

    public void OnClickAction(){

        binding.training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), TrainingActivity.class));


            }
        });

        binding.performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PerformanceActivity.class));

            }
        });


        binding.promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PromotionsActivity.class));

            }
        });


        binding.rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RewardsActivity.class));

            }
        });

    }
}