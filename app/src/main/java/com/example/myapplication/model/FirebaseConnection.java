package com.example.myapplication.model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

//public class FirebaseConnection {
//
//    private static FirebaseConnection instance;
//    private FirebaseDatabase database;
//
//    private FirebaseConnection(Context context) {
//        // Initialize the Firebase app
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setApiKey("YOUR_API_KEY")
//                .setApplicationId("YOUR_APPLICATION_ID")
//                .setDatabaseUrl("YOUR_DATABASE_URL")
//                .build();
//
//        FirebaseApp.initializeApp(context, options);
//
//        // Get the Firebase Realtime Database instance
//        database = FirebaseDatabase.getInstance();
//    }
//
//    // Singleton pattern for Firebase connection
//    public static FirebaseConnection getInstance() {
//        if (instance == null) {
//            instance = new FirebaseConnection();
//        }
//        return instance;
//    }
//
//    public FirebaseDatabase getDatabase() {
//        return database;
//    }


        // ...

import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

    public class FirebaseConnection {
        private static FirebaseConnection instance;
        private FirebaseDatabase database;

        private FirebaseConnection(Context context) {
            // Initialize the Firebase app
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApiKey("YOUR_API_KEY")
                    .setApplicationId("YOUR_APP_ID")
                    .setDatabaseUrl("https://yenibul-cfc63-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(context, options);

            // Get the Firebase Realtime Database instance
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }

        // Singleton pattern for Firebase connection
        public static FirebaseConnection getInstance(Context context) {
            if (instance == null) {
                instance = new FirebaseConnection(context);
            }
            return instance;
        }

        public FirebaseDatabase getDatabase() {
            return database;
        }

        public static void initFirebase(Context context) {
            try {
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setApiKey("AIzaSyD5gJZCOIKKB2srWeM_WdyXJRcmJVjID4Q")
                        .setApplicationId("1:911344098372:android:9d503fd4634527197a7011")
                        .setDatabaseUrl("https://dealersystem-33f7b-default-rtdb.firebaseio.com/")
                        .build();

                FirebaseApp.initializeApp(context, options);
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);

                Log.d("Firebase", "Firebase connection successful.");
            } catch (Exception e) {
                Log.e("Firebase", "Failed to initialize Firebase: " + e.getMessage());
            }
        }
    }