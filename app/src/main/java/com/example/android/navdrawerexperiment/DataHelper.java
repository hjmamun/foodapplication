package com.example.android.navdrawerexperiment;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public  class DataHelper {


   public static List<String> list=new ArrayList<>();


  public static void addToCart(String name){
       list.add(name);
       Log.d(TAG, "addToCart: "+list.size());
    }


    public List<String> productList(){
       List<String> helperClassModels=list;
       return  helperClassModels;
    }

}
