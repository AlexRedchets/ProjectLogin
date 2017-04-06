package com.alexredchets.projectlogin;

import android.app.Application;

import com.alexredchets.projectlogin.build.MainComponent;
import com.alexredchets.projectlogin.build.MainModule;
import com.alexredchets.projectlogin.dependecies.AppModule;
import com.alexredchets.projectlogin.dependecies.DaggerNetComponent;
import com.alexredchets.projectlogin.dependecies.NetComponent;
import com.alexredchets.projectlogin.dependecies.NetModule;
import com.alexredchets.projectlogin.ui.team.TeamInterface;

public class App extends Application {

    private NetComponent netComponent;
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://138.197.87.35:8080/"))
                .build();
    }

    public MainComponent getMainComponent(TeamInterface.TeamViewPresenter view){
        mainComponent = netComponent.plus(new MainModule(view));
        return mainComponent;
    }

    public void releaseMainComponent(){
        mainComponent = null;
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }


}
