package com.alexredchets.projectlogin.dependecies;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }


}
