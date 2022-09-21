package com.example.channelprotest_mvc.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.databinding.FragmentNewsBinding;
import com.example.channelprotest_mvc.databinding.FragmentReportBinding;


public class ReportFragment extends Fragment {

    public ReportFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentReportBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_report, container, false);
        View view = binding.getRoot();

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Report Sale");

        return view;
    }
}