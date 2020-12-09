package com.apiweb.controller;

import com.apiweb.model.Location;
import com.apiweb.model.MeetingPoll;
import com.apiweb.model.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.LOCATION_ID;
import static com.apiweb.var.MEETINGS;

@Service
public class FirebaseServiceMeeting {
    static public String saveMeeting(MeetingPoll meetingPoll) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(meetingPoll.getId()).set(meetingPoll);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    static public MeetingPoll getMeeting (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(MEETINGS).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            Location locationMeeting = FirebaseServiceLocation.getLocation(document.getString(LOCATION_ID));
            ArrayList<Person> contributors = FirebaseServiceContributor.getAllContributorForMeeting(id);
            return null;
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

