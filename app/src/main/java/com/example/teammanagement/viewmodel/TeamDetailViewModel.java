package com.example.teammanagement.viewmodel;


import com.example.teammanagement.Model.Player;
import com.example.teammanagement.Model.Team;


import java.io.Serializable;

import java.util.List;
import java.util.Observable;


public class TeamDetailViewModel extends Observable implements Serializable {

    private Team team;

    public TeamDetailViewModel(Team team) {this.team = team;}

    public String getFullTeamName(){
        return team.getFullName();
    }

    public String getTeamLoss(){return  String.valueOf(team.getLosses());}

    public String getTeamWin(){return  String.valueOf(team.getWins());}

    public List<Player> getPlayer () {
        return  team.getPlayers();
    }


}
