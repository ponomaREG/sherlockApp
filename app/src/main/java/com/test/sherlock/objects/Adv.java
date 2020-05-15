package com.test.sherlock.objects;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class Adv {
    private static String id_ad = "ca-app-pub-7354052962832258/1118758631";
    private static InterstitialAd mInterstitialAd;
    private static int count_of_tasks;
    private static final int PLATO = 12;
    private static Random random = new Random();




    private Adv(){}

    public static void initInstanceOfAds(Context context){
        mInterstitialAd = new InterstitialAd(context);
        String id_ad_test = "ca-app-pub-3940256099942544/1033173712";
        mInterstitialAd.setAdUnitId(id_ad_test);
    }

//    public static InterstitialAd getInstance(){
//        return mInterstitialAd;
//    }

    private static void loadAd(){
        if((!mInterstitialAd.isLoaded())&&(!mInterstitialAd.isLoading()))
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    private static void showAd(){
        if(mInterstitialAd.isLoaded()) mInterstitialAd.show();
    }

    private static int getCount(){
        return count_of_tasks;
    }

    private static void incCount(){
        count_of_tasks++;

    }

    private static void countEqualZero(){
        count_of_tasks = 0;
    }

    private static boolean isNeedToShowAd(){
        return count_of_tasks >= PLATO;
    }

    public static void showAdWithLoadedWith33PercentChance(){
        if(random.nextInt(3) == 2) {
            loadAd();
            showAd();
        }
    }

    public static void iterationAd(){
        incCount();
        loadAd();
        Log.d("COUNT",Adv.getCount()+"");
        if(isNeedToShowAd()){
            Log.d("SHOW AD", "AD");
            showAd();
            countEqualZero();
        }
    }

}
