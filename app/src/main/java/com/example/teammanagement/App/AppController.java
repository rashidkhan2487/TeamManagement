package com.example.teammanagement.App;

import android.app.Application;
import android.content.Context;

import com.example.teammanagement.Network.ApiFactory;
import com.example.teammanagement.Network.UsersService;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class AppController extends Application {

   static Context ctx;

    private UsersService usersService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        ctx = context;
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }


    public UsersService getUserService() {

        if (usersService == null) {
            usersService = ApiFactory.create(ctx);
        }

        return usersService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }


}
