package com.alexredchets.projectlogin.ui.team;

import android.util.Log;

import com.alexredchets.projectlogin.PlayerClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TeamPresenter implements TeamInterface.TeamPresenterView {

    private Retrofit retrofit;
    private TeamInterface.TeamViewPresenter view;

    private static final String TAG = TeamPresenter.class.getSimpleName();

    @Inject
    public TeamPresenter(Retrofit retrofit, TeamInterface.TeamViewPresenter view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void fetchData() {

        Log.i(TAG, "fetchData started");

        retrofit.create(PlayerClient.class).getPlayers("Russia")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(repos -> {
                            Log.e(TAG, "Successfully got data");

                            view.onComplete(repos);
                        },
                        throwable -> {
                            Log.e("Error", throwable.getMessage());
                            view.onError(throwable.getMessage());
                        });

    }

    @Override
    public void fetchDataDB() {

    }
}
