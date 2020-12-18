package com.apiweb.controller;

import com.apiweb.model.User;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@Service
public class FirebaseServiceUser {
    private static JsonStringEncoder FirebaseFirestore;

    static public String save(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(USERS).document(user.getId()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    static public User getConnected(String login) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documentReference = dbFireStore.collection(USERS).whereEqualTo(LOGIN, login).get().get().getDocuments();
        if (documentReference.size() == 1) {
            QueryDocumentSnapshot document = documentReference.get(0);
            User p = new User(document.getString(ID), document.getString(FIRSTNAME), document.getString(NAME), document.getString(LOGIN), document.getString(PWD), (ArrayList<String>) document.get(MEETING_VOTED),(ArrayList<String>)  document.get(MEETING_CREATED));
            return p;
        } else {
            return null;
        }
    }

    public static boolean getLoginAlreadyExist(String login) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documentReference = dbFireStore.collection(USERS).whereEqualTo(LOGIN, login).get().get().getDocuments();
        return documentReference.size() > 0;
    }
}

    // public static boolean getUserInfostoUpdate(String login) throws ExecutionException, InterruptedException {
       // Firestore dbFireStore = FirestoreClient.getFirestore();
        //List<QueryDocumentSnapshot> documentReference = dbFireStore.collection("users").whereEqualTo(LOGIN, login).get().get().getDocuments();
        //UserRecord userRecord = FirebaseAuth.getInstance().getUser(Id);
        // See the UserRecord reference doc for the contents of userRecord.
        //System.out.println("Successfully fetched user data: " + userRecord.getId());//
    //
    //




    // public static boolean getUserInfostoUpdate(String login) throws ExecutionException, InterruptedException {
       // Firestore dbFireStore = FirestoreClient.getFirestore();
        //List<QueryDocumentSnapshot> documentReference = dbFireStore.collection("users").whereEqualTo(LOGIN, login).get().get().getDocuments();
        //UserRecord userRecord = FirebaseAuth.getInstance().getUser(Id);
        // See the UserRecord reference doc for the contents of userRecord.
        //System.out.println("Successfully fetched user data: " + userRecord.getId());//
    //
    //




