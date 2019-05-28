package com.nguyendinhdoan.food.ui.sign_up;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.model.User;
import com.nguyendinhdoan.food.ui.base.BasePresenter;

public class SignUpPresenterImpl<V extends SignUpView> extends BasePresenter<V> implements SignUpPresenter<V> {

    private static final String TAG = SignUpPresenterImpl.class.getSimpleName();
    /**
     * perform sign up in app
     * @param phoneNumber phone number of user
     * @param name name of user
     * @param password password of user
     */
    @Override
    public void performSignUp(final String phoneNumber, final String name, final String password) {
        getMvpView().showLoading();

        Common.getUserObject().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getMvpView().hideLoading();

                if (phoneNumber.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    return;
                }

                // check phone number exist
                if (dataSnapshot.child(phoneNumber).exists()) {
                    getMvpView().onError(R.string.phone_number_already_register);
                } else {
                    User user = new User(name, password);
                    saveUserToDatabase(phoneNumber, user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getMvpView().hideLoading();
                Log.e(TAG, "onCancelled: perform login error" + databaseError.getMessage());
            }
        });
    }

    /**
     * save user register success to database
     * @param phoneNumber phone number of user
     * @param user - user register success
     */
    private void saveUserToDatabase(String phoneNumber, User user) {
        Common.getUserObject().child(phoneNumber)
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            getMvpView().onSuccess(true);
                        } else {
                            getMvpView().onError(R.string.sign_up_failed);
                        }
                    }
                });
    }
}
