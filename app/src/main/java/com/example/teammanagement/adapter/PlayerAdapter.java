package com.example.teammanagement.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.teammanagement.Model.Player;
import com.example.teammanagement.R;
import com.example.teammanagement.databinding.PlayerListBinding;
import com.example.teammanagement.viewmodel.PlayerViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


// Adapter class to display players in the detail view.
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.UserAdapterViewHolder> {

    private List<Player> playerList;

    public PlayerAdapter() {this.playerList = Collections.emptyList();}


    // Databinding layout with the adapter
    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlayerListBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.player_list ,parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(playerList.get(position));

    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  playerList.size();
    }


    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        PlayerListBinding playerListBinding;

        private UserAdapterViewHolder(PlayerListBinding itemUserBinding) {
           super(itemUserBinding.playerList);
            this.playerListBinding = itemUserBinding;
        }

        void bindUser(Player player){
            if(playerListBinding.getUserViewModel() == null){
                playerListBinding.setUserViewModel(new PlayerViewModel(player, itemView.getContext()));
            }else {
                playerListBinding.getUserViewModel().setTeam(player);
            }
        }
    }
}
