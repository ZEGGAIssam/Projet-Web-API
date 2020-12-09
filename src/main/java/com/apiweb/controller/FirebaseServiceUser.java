package com.apiweb.controller;

import com.apiweb.model.Person;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@Service
public class FirebaseServiceUser {
    static public String saveUser(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(USERS).document(person.getId()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    static public Person getUser (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(USERS).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            return new Person(document.getString(ID), document.getString(FIRSTNAME), document.getString(NAME), document.getString(LOGIN), document.getString(PWD));
        } else {
            return null;
        }
    }

    static public Person getConnected(String login) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documentReference = dbFireStore.collection(USERS).whereEqualTo(LOGIN, login).get().get().getDocuments();
        if (documentReference.size() == 1)
        {
            QueryDocumentSnapshot document = documentReference.get(0);
            Person p = new Person(document.getString(ID), document.getString(FIRSTNAME), document.getString(NAME), document.getString(LOGIN), document.getString(PWD));
            return  p;
        }
        else {
            return null;
        }
    }

    static public String deleteUser(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(USERS).document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public  static boolean getLoginAlreadyExist(String login) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> documentReference = dbFireStore.collection(USERS).whereEqualTo(LOGIN, login).get().get().getDocuments();
        return documentReference.size() > 0;
    }
}

