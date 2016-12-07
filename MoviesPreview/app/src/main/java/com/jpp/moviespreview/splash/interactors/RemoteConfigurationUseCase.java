package com.jpp.moviespreview.splash.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;
import com.jpp.moviespreview.core.interactors.UseCase;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by jpperetti on 6/12/16.
 */

public class RemoteConfigurationUseCase extends UseCase<Void, RemoteConfigurationDto> {
    @NonNull
    @Override
    protected Observable<RemoteConfigurationDto> buildObservableUseCase(Void aVoid) {
        return Observable.create(new Observable.OnSubscribe<RemoteConfigurationDto>() {
            @Override
            public void call(Subscriber<? super RemoteConfigurationDto> subscriber) {
                try {
                    Call<RemoteConfigurationDto> call = getApiInstance().configurations(BuildConfig.API_KEY);
                    Response<RemoteConfigurationDto> response = call.execute();
                    RemoteConfigurationDto remoteConfiguration = response.body();
                    if (remoteConfiguration == null) {
                        subscriber.onError(new Exception("Unable to retrieve configuration from server"));
                    } else {
                        subscriber.onNext(remoteConfiguration);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }

            }
        });
    }
}
