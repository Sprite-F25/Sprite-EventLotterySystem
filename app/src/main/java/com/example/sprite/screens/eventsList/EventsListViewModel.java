package com.example.sprite.screens.eventsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EventsListViewModel extends ViewModel {

    private final MutableLiveData<List<String>> events;

    public EventsListViewModel() {
        events = new MutableLiveData<>();
        // Example data - we'll add firestore stuff later and convert from Strings to Events
        List<String> initialEvents = new ArrayList<>();
        initialEvents.add("Event 1");
        initialEvents.add("Event 2");
        events.setValue(initialEvents);     // getValue and setValue are methods for MutableLiveData objects
    }

    public LiveData<List<String>> getEvents() {
        return events;
    }

    public void addEvent(String event) {
        List<String> current = events.getValue();
        if (current != null) {
            current.add(event);
            events.setValue(current);
        }
    }
}
