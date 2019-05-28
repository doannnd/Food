package com.nguyendinhdoan.food.ui.sign_in;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.model.User;
import com.nguyendinhdoan.food.ui.base.BasePresenter;

public class SignInPresenterImpl<V extends SignInView> extends BasePresenter<V> implements SignInPresenter<V>{

    private static final String TAG = SignInPresenterImpl.class.getSimpleName();

    /**
     * perform sign in
     * @param phoneNumber phone of user
     * @param password password of user
     */
    @Override
    public void performSignIn(final String phoneNumber, final String password) {
        getMvpView().showLoading();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            return;
        }

        Common.getUserObject().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getMvpView().hideLoading();

                // check phone number has exist
                if (!dataSnapshot.child(phoneNumber).exists()) {
                    getMvpView().onError(R.string.user_not_exist);
                    return;
                }

                // check user exist
                User user = dataSnapshot.child(phoneNumber).getValue(User.class);
                if (user == null) {
                    return;
                }

                // check password correct
                if (user.getPassword().equals(password)) {
                    getMvpView().onSuccess(true);
                    Common.currentUser = user;
                } else {
                    getMvpView().onError(R.string.password_failed);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getMvpView().hideLoading();
                Log.e(TAG, "onCancelled: perform login error" + databaseError.getMessage());
            }
        });
    }
}
