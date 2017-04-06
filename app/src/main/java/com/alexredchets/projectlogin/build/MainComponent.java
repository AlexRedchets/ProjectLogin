package com.alexredchets.projectlogin.build;

import com.alexredchets.projectlogin.ui.team.TeamFragment;

import dagger.Subcomponent;

@CustomScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    TeamFragment inject(TeamFragment fragment);
}
