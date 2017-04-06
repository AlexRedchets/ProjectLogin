package com.alexredchets.projectlogin.ui.team;

import com.alexredchets.projectlogin.model.Player;

import java.util.List;

public interface TeamInterface {

    interface TeamViewPresenter{

        void onComplete(List<Player> data);

        void onError(String message);
    }

    interface TeamPresenterView{

        void fetchData();

        void fetchDataDB();
    }

}
