package com.example.teammanagement.viewmodel;

import android.content.Context;


import com.example.teammanagement.Model.Player;


import androidx.databinding.BaseObservable;


public class PlayerViewModel extends BaseObservable {

    private Player player;
    private Context context;

    public PlayerViewModel(Player player, Context context){
        this.player = player;
        this.context = context;
    }


    public String playerFName () {
       return  player.getFirstName();
    }

    public String playerLName () {
        return  player.getLastName();
    }


    public String postion () {
        return  String.valueOf(player.getPosition());
    }



    public void setTeam(Player player) {
        this.player = player;
        notifyChange();
    }
}
