package com.jpp.moviespreview.core.network;


import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
     * @return - an Call with the response from the server.
     */
    @GET("configuration")
    Call<RemoteConfigurationDto> configurations(@Query("api_key") String apiKey);


    /**
     * Retrieve the top rated list of movies.
     *
     * @param page   - the page to retrieve.
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Call with the response from the server.
     */
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MoviePageDto> topRated(@Query("page") int page, @Query("api_key") String apiKey);


    /**
     * Retrieve the of movies currently in theatres.
     *
     * @param page   - the page to retrieve.
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Call with the response from the server.
     */
    @GET("movie/now_playing")
    Call<MoviePageDto> nowPlaying(@Query("page") int page, @Query("api_key") String apiKey);

    /**
     * Retrieve a page that contains all the movies genres.
     *
     * @param apiKey - the api key to use in the authentication process.
     * @return - an Call with the response from the server.
     */
    @GET("genre/movie/list")
    Call<MovieGenrePage> genres(@Query("api_key") String apiKey);


    /**
     * Retrieve the result of a search query to the movies API.
     *
     * @param apiKey       - the api key to use in the authentication process.
     * @param query        - the query to look in the search.
     * @param includeAdult - true if adult rated movies should be included in the search, false any other case.
     * @return - a Call with the response from the server.
     */
    @GET("search")
    Call<MoviePageDto> search(@Query("api_key") String apiKey, @Query("query") String query, @Query("include_adult") boolean includeAdult);


}
