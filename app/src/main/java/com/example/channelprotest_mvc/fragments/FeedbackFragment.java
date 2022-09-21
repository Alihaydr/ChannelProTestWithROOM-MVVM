package com.example.channelprotest_mvc.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.FragmentFeedbackBinding;


public class FeedbackFragment extends Fragment {


    public FeedbackFragment() {

     }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFeedbackBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_feedback, container, false);
        View view = binding.getRoot();
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Feedback");


        return view;
    }
}