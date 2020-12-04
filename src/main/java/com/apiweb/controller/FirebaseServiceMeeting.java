package com.apiweb.controller;

import com.apiweb.model.Location;
import com.apiweb.model.Meeting;
import com.apiweb.model.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.LOCATION_ID;
import static com.apiweb.var.MEETINGS;

@Service
public class FirebaseServiceMeeting {
    static public String saveMeeting(Meeting meeting) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(meeting.getId()).set(meeting);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    static public Meeting getMeeting (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(MEETINGS).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            Location locationMeeting = FirebaseServiceLocation.getLocation(document.getString(LOCATION_ID));
            ArrayList<Person> contributors = FirebaseServiceContributor.getAllContributorForMeeting(id);
            return new Meeting(document, locationMeeting, contributors);
        } else {
            return null;
        }
    }


    static public String deleteMeeting(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}

