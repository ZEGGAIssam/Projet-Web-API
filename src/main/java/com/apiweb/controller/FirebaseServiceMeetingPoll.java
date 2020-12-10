package com.apiweb.controller;

import com.apiweb.model.MeetingPoll;
import com.apiweb.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@Service
public class FirebaseServiceMeetingPoll {
    static public String saveMeeting(MeetingPoll meetingPoll) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(meetingPoll.getId()).set(meetingPoll);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }


    static public String deleteMeeting(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static void add(MeetingPoll meetingPoll) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(MEETINGS).document(meetingPoll.getId()).set(meetingPoll);
    }

    public static MeetingPoll get(String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentSnapshot document = dbFireStore.collection(MEETINGS).document(id).get().get();
        if (document.exists())
        {
            MeetingPoll meetingPoll = new MeetingPoll(document.getString(ID), document.getString(NAME), (HashMap<String, Long>) document.get(CHOIX_DATE), (HashMap<String, Long>) document.get(CHOIX_LOC));
            return  meetingPoll;
        }
        else {
            return null;
        }
    }

    public static ArrayList<String> getAllId() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentList = dbFireStore.collection(MEETINGS).listDocuments();
        ArrayList<String> arString = new ArrayList<>();
        for ( DocumentReference d: documentList) {
            arString.add(d.get().get().getString(NAME));
        }
        return arString;
    }
}

