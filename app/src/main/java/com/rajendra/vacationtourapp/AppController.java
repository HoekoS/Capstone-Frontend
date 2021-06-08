package com.rajendra.vacationtourapp;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;

import com.rajendra.vacationtourapp.model.Rest.RestSingleTon;


public class AppController extends Application {
    private RestSingleTon rest;
    private  static AppController instance;

    @Override
    public void onTerminate(){
        super.onTerminate();

    }
    @Override
    protected void attachBaseContext(Context base) {super.attachBaseContext(base);}
    public static synchronized AppController getInstance() {return instance;}

    public static synchronized RestSingleTon getRest() {return instance.rest;}
    @Override
    public void  onCreate(){
        super.onCreate();
        rest = new RestSingleTon(this);
        instance = this;

    }




}

