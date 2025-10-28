package com.example.sprite.screens.eventsList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sprite.R;

public class EventsListFragment extends Fragment {

    private EventsListViewModel mViewModel;
    private TextView textView;

    public static EventsListFragment newInstance() {
        return new EventsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Link UI to view
        View viewName = inflater.inflate(R.layout.fragment_events_list, container, false);
        textView = viewName.findViewById(R.id.text_events);

        // Set up ViewModel
        mViewModel = new ViewModelProvider(this).get(EventsListViewModel.class);

        // observe LiveData - so that it automatically updatees
        mViewModel.getEvents().observe(getViewLifecycleOwner(), events -> {
            textView.setText("Events count: " + events.size());
        });

        return viewName;
    }
}
