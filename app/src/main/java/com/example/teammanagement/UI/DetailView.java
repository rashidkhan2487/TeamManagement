package com.example.teammanagement.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.teammanagement.Model.Team;
import com.example.teammanagement.R;
import com.example.teammanagement.adapter.PlayerAdapter;
import com.example.teammanagement.adapter.TeamAdapter;
import com.example.teammanagement.databinding.ActivityDetailViewBinding;
import com.example.teammanagement.viewmodel.TeamDetailViewModel;
import com.example.teammanagement.viewmodel.TeamsViewModel;

public class DetailView extends AppCompatActivity {


    ActivityDetailViewBinding detailViewBinding;
    TeamDetailViewModel teamDetailViewModel;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
         team = (Team) getIntent().getSerializableExtra("TEAM_DATA");


        detailViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_view);
        teamDetailViewModel = new TeamDetailViewModel(team);
        detailViewBinding.setUserViewModel(teamDetailViewModel);
        setUpListOfUsersView(detailViewBinding.playerList);

    }

    private void setUpListOfUsersView(RecyclerView listUser) {
        PlayerAdapter userAdapter = new PlayerAdapter();
        listUser.setAdapter(userAdapter);
        listUser.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = (PlayerAdapter) detailViewBinding.playerList.getAdapter();
        userAdapter.setPlayerList(team.getPlayers());
    }
}
