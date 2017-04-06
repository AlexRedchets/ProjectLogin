package com.alexredchets.projectlogin;

import com.alexredchets.projectlogin.model.Player;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayerClient {

    @GET("player/{team}")
    Observable<List<Player>> getPlayers(
            @Path("team") String team

    );

}
