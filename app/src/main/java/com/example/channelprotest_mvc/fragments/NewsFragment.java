package com.example.channelprotest_mvc.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.ViewModel.InboxViewModel;
import com.example.channelprotest_mvc.ViewModel.NewsViewModel;
import com.example.channelprotest_mvc.activities.MainActivity;
import com.example.channelprotest_mvc.adapters.NewsAdapter;
import com.example.channelprotest_mvc.databinding.FragmentHomeBinding;
import com.example.channelprotest_mvc.databinding.FragmentNewsBinding;
import com.example.channelprotest_mvc.room.Inbox;
import com.example.channelprotest_mvc.room.News;

import java.util.List;


public class NewsFragment extends Fragment {


    public NewsFragment() {

    }

    private MainActivity mainActivity;

    private NewsViewModel newsViewModel;
    LinearLayoutManager HorizontalLayout;
    Toolbar toolbar;
    public ActionBar actionBar;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNewsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_news, container, false);
        View view = binding.getRoot();

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("News");


        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerNews.setLayoutManager(HorizontalLayout);
        binding.recyclerNews.setHasFixedSize(true);

        NewsAdapter adapter = new NewsAdapter(getActivity());
        binding.recyclerNews.setAdapter(adapter);

        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        newsViewModel.getAllNews().observe(requireActivity(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {

                if (news != null) {
                    adapter.setNews(news);
                }

            }
        });

        return view ;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {



        }

        return super.onOptionsItemSelected(item);
    }
}