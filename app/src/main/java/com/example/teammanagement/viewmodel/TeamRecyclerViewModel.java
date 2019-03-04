package com.example.teammanagement.viewmodel;

import android.content.Context;

import android.view.View;

import com.example.teammanagement.Model.Team;
import com.example.teammanagement.UI.MainActivity;


import androidx.databinding.BaseObservable;


// Viewmodel that extend BaseObservable to implement reactive programming
public class TeamRecyclerViewModel extends BaseObservable {

    private Team team;
    private Context context;

    public TeamRecyclerViewModel(Team user, Context context){
        this.team = user;
        this.context = context;
    }


    public String teamName () {
       return  team.getFullName();
    }

    public String teamWon () {
        return  String.valueOf(team.getWins());
    }

    public String teamLoss () {
        return  String.valueOf(team.getLosses());
    }

    public void onItemClick(View v){
        context.startActivity(MainActivity.fillDetail(v.getContext(), team));
    }


}
