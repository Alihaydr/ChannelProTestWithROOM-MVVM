package com.example.channelprotest_mvc.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.channelprotest_mvc.R;
import com.example.channelprotest_mvc.ViewModel.InboxViewModel;
import com.example.channelprotest_mvc.ViewModel.NewsViewModel;
import com.example.channelprotest_mvc.adapters.InboxAdapter;
import com.example.channelprotest_mvc.adapters.NewsAdapter;
import com.example.channelprotest_mvc.databinding.FragmentInboxBinding;
import com.example.channelprotest_mvc.databinding.FragmentNewsBinding;
import com.example.channelprotest_mvc.room.Inbox;
import com.example.channelprotest_mvc.room.News;

import java.util.List;


public class InboxFragment extends Fragment {


    public InboxFragment() {

    }
    LinearLayoutManager HorizontalLayout;

    InboxViewModel inboxViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentInboxBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_inbox, container, false);
        View view = binding.getRoot();
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerInbox.setLayoutManager(HorizontalLayout);
        binding.recyclerInbox.setHasFixedSize(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Inbox");

        InboxAdapter adapter = new InboxAdapter();
        binding.recyclerInbox.setAdapter(adapter);

        inboxViewModel = new ViewModelProvider(requireActivity()).get(InboxViewModel.class);
        inboxViewModel.getAllInboxes().observe(requireActivity(), new Observer<List<Inbox>>() {
            @Override
            public void onChanged(List<Inbox> inboxes) {

                if (inboxes != null) {
                    adapter.setInboxes(inboxes);
                }

            }
        });


        return view;
    }
}