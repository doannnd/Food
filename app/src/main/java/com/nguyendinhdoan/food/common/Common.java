package com.nguyendinhdoan.food.common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyendinhdoan.food.model.User;
import com.nguyendinhdoan.food.utils.ConstantUtils;

public class Common {

    public static User currentUser;

    public static DatabaseReference getUserObject() {
        return FirebaseDatabase.getInstance().getReference(ConstantUtils.USERS_TABLE_NAME);
    }

    public static DatabaseReference getCategoryObject() {
        return FirebaseDatabase.getInstance().getReference(ConstantUtils.CATEGORIES_TABLE_NAME);
    }

    public static DatabaseReference getFoodObject() {
        return FirebaseDatabase.getInstance().getReference(ConstantUtils.FOOD_TABLE_NAME);
    }
}
