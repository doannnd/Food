package com.nguyendinhdoan.food.ui.sign_up;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.model.User;
import com.nguyendinhdoan.food.utils.CommonUtils;

public class SignUpPresenterImpl implements SignUpPresenter {

    private static final String TAG = SignUpPresenterImpl.class.getSimpleName();

    private SignUpView signUpView;

    public SignUpPresenterImpl(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void performSignUp(final String phoneNumber, final String name, final String password) {

        signUpView.showLoading();

        final DatabaseReference userObject = FirebaseDatabase.getInstance().getReference(CommonUtils.USERS_TABLE_NAME);
        userObject.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signUpView.hideLoading();

                if (phoneNumber.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    return;
                }

                if (dataSnapshot.child(phoneNumber).exists()) {
                    signUpView.onError(R.string.phone_number_already_register);
                } else {
                    User user = new User(name, password);
                    saveUserToDatabase(userObject, phoneNumber, user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                signUpView.hideLoading();

                Log.e(TAG, "onCancelled: perform login error" + databaseError.getMessage());
            }
        });
    }

    private void saveUserToDatabase(DatabaseReference userObject, String phoneNumber, User user) {
        userObject.child(phoneNumber)
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            signUpView.onSuccess(true);
                        } else {
                            signUpView.onError(R.string.sign_up_failed);
                        }
                    }
                });
    }
}
