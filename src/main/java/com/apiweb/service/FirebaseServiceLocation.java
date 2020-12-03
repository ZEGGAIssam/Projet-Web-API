package com.apiweb.service;

import com.apiweb.model.Location;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceLocation {
    public String saveLocation(Location loc) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("locations").document(loc.getId()).set(loc);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    public Location getLocation (String id) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection("locations").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            return  new Location(document);
        } else {
            return null;
        }
    }
    public String updateLocation(Location loc) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("locations").document(loc.getId()).set(loc);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteLocation(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("locations").document(id).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}

