package com.example.sprite.screens.eventsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sprite.Models.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class EventsListViewModel extends ViewModel {

    private final MutableLiveData<List<Event>> events;

    public EventsListViewModel() {
        events = new MutableLiveData<>();
        // Example data - we'll add firestore stuff later and convert from Strings to Events
        List<Event> initialEvents = new ArrayList<>();
        initialEvents.add(new Event("title", "description", "location", new Date(), 20.00));
        initialEvents.add(new Event("title2", "description2", "location2", new Date(), 22.00));
        events.setValue(initialEvents);     // getValue and setValue are methods for MutableLiveData objects
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        List<Event> current = events.getValue();
        if (current != null) {
            current.add(event);
            events.setValue(current);
        }
    }
}
