package com.github.nekdenis.facetracker.db;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDb implements Database {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference countRef = database.getReference("peopleInRoom");

    @Override public void save(int count) {
        countRef.setValue(count);
    }
}
