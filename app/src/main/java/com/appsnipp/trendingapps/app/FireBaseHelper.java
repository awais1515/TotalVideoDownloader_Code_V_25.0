package com.appsnipp.trendingapps.app;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseHelper {
    //
    static FirebaseDatabase firebaseDatabase;
    static DatabaseReference databaseReference;

    public static void loadAdSettingsFromFireBase() {
        // load 2 values from the firebase which should be added manually to firebase
        // write the 2 updated values in the sharedPreferences
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        //data retrival
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//

                String admobBannerAdUnit_1 = dataSnapshot.child(SharedPref.KEY_ADMOB_BANNER_AD_1).getValue().toString();
                String admobBannerAdUnit_2 = dataSnapshot.child(SharedPref.KEY_ADMOB_BANNER_AD_2).getValue().toString();
                String admobBannerAdUnit_3 = dataSnapshot.child(SharedPref.KEY_ADMOB_BANNER_AD_3).getValue().toString();
                String admobBannerAdUnit_4 = dataSnapshot.child(SharedPref.KEY_ADMOB_BANNER_AD_4).getValue().toString();
                String admobInterAdUnit = dataSnapshot.child(SharedPref.KEY_ADMOB_INTER).getValue().toString();
                String admobInterAdUnit_2 = dataSnapshot.child(SharedPref.KEY_ADMOB_INTER_2).getValue().toString();
                //fb ads
                String fbadmobBannerAdUnit_1 = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_BANNER_AD_1).getValue().toString();
                String fbadmobBannerAdUnit_2 = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_BANNER_AD_2).getValue().toString();
                String fbadmobBannerAdUnit_3 = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_BANNER_AD_3).getValue().toString();
                String fbadmobBannerAdUnit_4 = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_BANNER_AD_4).getValue().toString();
                String fbadmobInterAdUnit = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_INTER_AD_1).getValue().toString();
                String fbadmobInterAdUnit_2 = dataSnapshot.child(SharedPref.KEY_FB_ADMOB_INTER_AD_2).getValue().toString();
                // ads type fb/admob
                String adsType = dataSnapshot.child(SharedPref.KEY_ADS).getValue().toString();
                SharedPref.write(SharedPref.KEY_ADMOB_BANNER_AD_1, admobBannerAdUnit_1);
                SharedPref.write(SharedPref.KEY_ADMOB_BANNER_AD_2, admobBannerAdUnit_2);
                SharedPref.write(SharedPref.KEY_ADMOB_BANNER_AD_3, admobBannerAdUnit_3);
                SharedPref.write(SharedPref.KEY_ADMOB_BANNER_AD_4, admobBannerAdUnit_4);
                SharedPref.write(SharedPref.KEY_ADMOB_INTER, admobInterAdUnit);
                SharedPref.write(SharedPref.KEY_ADMOB_INTER_2, admobInterAdUnit_2);
                // fb ads
                SharedPref.write(SharedPref.KEY_FB_ADMOB_BANNER_AD_1, fbadmobBannerAdUnit_1);
                SharedPref.write(SharedPref.KEY_FB_ADMOB_BANNER_AD_2, fbadmobBannerAdUnit_2);
                SharedPref.write(SharedPref.KEY_FB_ADMOB_BANNER_AD_3, fbadmobBannerAdUnit_3);
                SharedPref.write(SharedPref.KEY_FB_ADMOB_BANNER_AD_4, fbadmobBannerAdUnit_4);
                SharedPref.write(SharedPref.KEY_FB_ADMOB_INTER_AD_1, fbadmobInterAdUnit);
                SharedPref.write(SharedPref.KEY_FB_ADMOB_INTER_AD_2, fbadmobInterAdUnit_2);
                SharedPref.write(SharedPref.KEY_ADS, adsType);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
