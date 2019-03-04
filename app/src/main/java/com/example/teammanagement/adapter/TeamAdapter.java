package com.example.teammanagement.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.teammanagement.Model.Team;
import com.example.teammanagement.R;
import com.example.teammanagement.databinding.ItemUserBinding;
import com.example.teammanagement.viewmodel.TeamRecyclerViewModel;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;



public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.UserAdapterViewHolder> {

    private List<Team> teamList;

    public TeamAdapter() {this.teamList = Collections.emptyList();}

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user ,parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(teamList.get(position));

    }

    @Override
    public int getItemCount() {
        return  teamList.size();
    }

    public void updateUserList(final String updateBy){

        Collections.sort(this.teamList, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                switch (updateBy) {
                    case "Team":
                        return o1.getFullName().compareTo(o2.getFullName());
                    case "Won":
                        return  String.valueOf(o1.getWins()).compareTo( String.valueOf(o2.getWins()));
                    case "Lose":
                        return  String.valueOf(o1.getLosses()).compareTo( String.valueOf(o2.getLosses()));
                }
                return 0;
            }
        });

        notifyDataSetChanged();
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
        notifyDataSetChanged();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemUserBinding itemUserBinding) {
           super(itemUserBinding.itemPeople);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(Team user){

                mItemUserBinding.setUserViewModel(new TeamRecyclerViewModel(user, itemView.getContext()));

        }
    }
}
