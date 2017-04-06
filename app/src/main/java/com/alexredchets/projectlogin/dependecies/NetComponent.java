package com.alexredchets.projectlogin.dependecies;

import com.alexredchets.projectlogin.build.MainComponent;
import com.alexredchets.projectlogin.build.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    MainComponent plus (MainModule module);
}