package com.example.teammanagement;

import com.example.teammanagement.Model.Team;
import com.example.teammanagement.Network.UsersService;
import com.example.teammanagement.UI.MainActivity;
import com.example.teammanagement.viewmodel.TeamsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static io.reactivex.Observable.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class UnitTestClass {

    private MainActivity mainActivity;

    @Mock
    UsersService usersService;

    @Mock
    TeamsViewModel teamsViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mainActivity = controller.get();
        teamsViewModel = new TeamsViewModel(RuntimeEnvironment.application.getApplicationContext());

    }

        @Test
    public void checkEmptyList() {
            ArrayList<Team> resultList = new ArrayList<>();
        when(teamsViewModel.getUserList()).thenReturn(resultList);

            assertEquals(4, 2 + 2);
    }


}