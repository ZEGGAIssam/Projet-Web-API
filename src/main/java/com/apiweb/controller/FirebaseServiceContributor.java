package com.apiweb.controller;

import com.apiweb.model.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@Service
public class FirebaseServiceContributor {
    static public ArrayList<Person> getAllContributorForMeeting(String meetingId) throws ExecutionException, InterruptedException {
        ArrayList contributors = new ArrayList<Person>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> collectionsApiFuture = dbFirestore.collection(CONTRIBUTORS).whereEqualTo(MEETING_ID, meetingId).get();
        for (DocumentSnapshot documentPerson : collectionsApiFuture.get().getDocuments()) {
            contributors.add(FirebaseServiceUser.getUser(documentPerson.getString(USER_ID)));
        }
        return contributors;
    }

    public static String addContributortoMeeting(Map<String, Object> json) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> listDocument  = dbFirestore.collection(CONTRIBUTORS).whereEqualTo(MEETING_ID, json.get(MEETING_ID)).whereEqualTo(USER_ID, json.get(USER_ID)).get().get().getDocuments();
        if (listDocument.size() > 0)
        {
            return "Cette relation existe deja";
        }
        else
        {
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(CONTRIBUTORS).document(GenerateId.generateID()).set(json);
            return collectionsApiFuture.get().getUpdateTime().toString();
        }

    }
}

