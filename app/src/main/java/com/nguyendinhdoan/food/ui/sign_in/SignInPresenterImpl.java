package com.nguyendinhdoan.food.ui.sign_in;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.model.User;
import com.nguyendinhdoan.food.utils.CommonUtils;

public class SignInPresenterImpl implements SignInPresenter{

    private static final String TAG = SignInPresenterImpl.class.getSimpleName();

    private SignInView signInView;

    public SignInPresenterImpl(SignInView signInView) {
        this.signInView = signInView;
    }

    @Override
    public void performSignIn(final String phoneNumber, final String password) {

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            return;
        }

        signInView.showLoading();

        DatabaseReference userObject = FirebaseDatabase.getInstance().getReference(CommonUtils.USERS_TABLE_NAME);
        userObject.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                signInView.hideLoading();

                // check phone number has exist
                if (dataSnapshot.child(phoneNumber).exists()) {
                    // get user by phone number and check user exist
                    User user = dataSnapshot.child(phoneNumber).getValue(User.class);
                    if (user == null) {
                        return;
                    }
                    // check password correct
                    if (user.getPassword().equals(password)) {
                        signInView.onSuccess(true);
//                    --------------   Save current user

                        Common.currentUser = user;
                    } else {
                        signInView.onError(R.string.password_failed);
                    }

                } else {
                    signInView.onError(R.string.user_not_exist);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                signInView.hideLoading();

                Log.e(TAG, "onCancelled: perform login error" + databaseError.getMessage());
            }
        });
    }
}
