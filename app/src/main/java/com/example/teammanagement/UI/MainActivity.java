package com.example.teammanagement.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.teammanagement.Model.Team;
import com.example.teammanagement.R;
import com.example.teammanagement.adapter.TeamAdapter;
import com.example.teammanagement.viewmodel.TeamsViewModel;
import com.example.teammanagement.databinding.ActivityMainBinding;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private ActivityMainBinding activityMainBinding;
    private TeamsViewModel userViewModel;
    private static final String TEAM_DATA = "TEAM_DATA";
    TeamAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setUpObserver(userViewModel);
        setUpListOfUsersView(activityMainBinding.listUser);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }


    public void sortMyList(String sortByValue) {
        userAdapter.updateUserList(sortByValue);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
            case R.id.sort_by_name:
                sortMyList("Team");
                return true;
            case R.id.sort_by_win:
                sortMyList("Won");
                return true;
            case R.id.sort_by_lose:
                sortMyList("Lose");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.onClickFabToLoad();
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = new TeamsViewModel(this);
            activityMainBinding.setUserViewModel(userViewModel);

    }


    public static Intent fillDetail(Context context, Team team) {
        Intent intent = new Intent(context, DetailView.class);
        intent.putExtra(TEAM_DATA, team);
        return intent;
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }



    private void setUpListOfUsersView(RecyclerView listUser) {
        TeamAdapter userAdapter = new TeamAdapter();
        listUser.setAdapter(userAdapter);
        listUser.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof TeamsViewModel) {
            userAdapter = (TeamAdapter) activityMainBinding.listUser.getAdapter();
            TeamsViewModel userViewModel = (TeamsViewModel) o;
            userAdapter.setTeamList(userViewModel.getUserList());
        }
    }
}
