package com.jpp.moviespreview.core.network;


import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface that defines the signature of the API to access.
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public interface MoviesPreviewApi {


    /**
     * Retrieve the RemoteConfiguration from the server.
     *
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Observable with the response from the server.
     */
    @GET("configuration")
    Observable<RemoteConfigurationDto> configurations(@Query("api_key") String apiKey);


    /**
     * Retrieve the top rated list of movies.
     *
     * @param page   - the page to retrieve.
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Observable with the response from the server.
     */
    @GET("movie/top_rated")
    Observable<MoviePageDto> topRated(@Query("page") String page, @Query("api_key") String apiKey);


    /**
     * Retrieve a page that contains all the movies genres.
     *
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Observable with the response from the server.
     */
    @GET("genre/movie/list")
    Observable<MovieGenrePage> genres(@Query("api_key") String apiKey);


}
