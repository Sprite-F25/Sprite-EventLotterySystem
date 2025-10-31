package com.example.sprite.Controllers;


import android.util.Log;

import com.example.sprite.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication_Service {
    private static final String TAG = "AuthService";
    private FirebaseAuth mAuth;
    private DatabaseService databaseService;

    public Authentication_Service() {
        mAuth = FirebaseAuth.getInstance();
        databaseService = new DatabaseService();
    }

    public interface AuthCallback {
        void onSuccess(User user);
        void onFailure(String error);
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public boolean isUserLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public void signInAnonymously(AuthCallback callback) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                // Create or get user profile
                                getUserProfile(firebaseUser.getUid(), callback);
                            }
                        } else {
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    public void signInWithEmail(String email, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                getUserProfile(firebaseUser.getUid(), callback);
                            }
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    public void createUserWithEmail(String email, String password, String name, User.UserRole role, AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                // Create user profile
                                User user = new User(firebaseUser.getUid(), email, name, role);
                                databaseService.createUser(user, task1 -> {
                                    if (task1.isSuccessful()) {
                                        callback.onSuccess(user);
                                    } else {
                                        callback.onFailure("Failed to create user profile");
                                    }
                                });
                            }
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }

    public void getUserProfile(String userId, AuthCallback callback) {
        databaseService.getUser(userId, task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    User user = task.getResult().toObject(User.class);
                    if (user != null) {
                        callback.onSuccess(user);
                    } else {
                        callback.onFailure("Failed to parse user data");
                    }
                } else {
                    // Create new user profile for anonymous user
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        User newUser = new User(userId, firebaseUser.getEmail() != null ? firebaseUser.getEmail() : "anonymous@example.com", "Anonymous User", User.UserRole.ENTRANT);
                        databaseService.createUser(newUser, task1 -> {
                            if (task1.isSuccessful()) {
                                callback.onSuccess(newUser);
                            } else {
                                callback.onFailure("Failed to create user profile");
                            }
                        });
                    }
                }
            } else {
                callback.onFailure("Failed to get user profile");
            }
        });
    }

    public void updateUserProfile(User user, AuthCallback callback) {
        databaseService.updateUser(user, task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(user);
            } else {
                callback.onFailure("Failed to update user profile");
            }
        });
    }
}
