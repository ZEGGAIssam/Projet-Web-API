package com.apiweb.service;

import com.apiweb.model.Person;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceUser {
    public String saveUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(person.getId()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    public Person getUserDetails (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            Person person = new Person(document);
            return person;
        } else {
            return null;
        }
    }
    public String updateUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(person.getId()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}

