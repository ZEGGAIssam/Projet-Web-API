package com.apiweb.controller;

import com.apiweb.model.Location;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.apiweb.var.LOCATIONS;

@Service
public class FirebaseServiceLocation {
    static public String saveLocation(Location loc) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(LOCATIONS).document(loc.getId()).set(loc);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    static public Location getLocation (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(LOCATIONS).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            return  null;
        } else {
            return null;
        }
    }

    static public String deleteLocation(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(LOCATIONS).document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}

