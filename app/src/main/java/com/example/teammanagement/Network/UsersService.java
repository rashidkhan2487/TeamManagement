package com.example.teammanagement.Network;

import com.example.teammanagement.Model.Team;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UsersService {

    @GET
    Observable<List<Team>> fetchUsers(@Url String url);
}
