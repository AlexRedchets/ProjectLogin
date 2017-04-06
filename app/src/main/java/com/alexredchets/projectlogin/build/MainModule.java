package com.alexredchets.projectlogin.build;

import com.alexredchets.projectlogin.ui.team.TeamInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private TeamInterface.TeamViewPresenter view;

    public MainModule(TeamInterface.TeamViewPresenter view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    TeamInterface.TeamViewPresenter getView(){
        return view;
    }
}
