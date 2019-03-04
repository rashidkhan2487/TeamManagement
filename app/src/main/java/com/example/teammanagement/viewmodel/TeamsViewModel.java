package com.example.teammanagement.viewmodel;

import android.content.Context;

import android.view.View;

import com.example.teammanagement.App.AppController;
import com.example.teammanagement.Model.Team;
import com.example.teammanagement.Network.UsersService;
import com.example.teammanagement.R;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.example.teammanagement.Utils.Constant.JSON_URL;


/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public class TeamsViewModel extends Observable implements Serializable {

    public ObservableInt userRecycler;

    public List<Team> userList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TeamsViewModel(@NonNull Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
        userRecycler = new ObservableInt(View.GONE);
    }

    public void onClickFabToLoad() {
       // initializeViews();
        fetchUsersList();
    }

    //It is "public" to show an example of test

    public void fetchUsersList() {

        AppController appController = AppController.create(context);
        UsersService usersService = appController.getUserService();

          Disposable disposable = usersService.fetchUsers(JSON_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Team>>() {
                    @Override
                    public void accept(List<Team> teams) throws Exception {

                        updateUserDataList(teams);

                        userRecycler.set(View.VISIBLE);
                    }



                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        userRecycler.set(View.VISIBLE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void updateUserDataList(List<Team> teams) {
        userList.addAll(teams);
        setChanged();
        notifyObservers();
    }

    public List<Team> getUserList() {
        return userList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}

