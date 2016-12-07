# Movies Preview - an Android application to put in practice some ideas

Movies Preview v1.0-beta is a simple application that allows the user to see a list the most rated movies from the 
[TheMovieDB](https://www.themoviedb.org/) API.

The main goal of this repository is to practice new techinques and libraries in Android and to test some implementation ideas
in order to evaluate how factible they are in a productive environment.

## App behavior
[You can see the app behavior in this YouTube video](https://youtu.be/auH-uHLxwrk)

## Cool ideas to test

### MVP implementation and Activity lifecycle:
How about using a Fragment to handle the Activity lifecycle of a Presenter that has a View (an Activity) attached to it?

 - The `Activity` holds a `Fragment` -[`StateFragment`](https://github.com/perettijuan/moviespreview/blob/master/MoviesPreview/app/src/main/java/com/jpp/moviespreview/core/mvp/StateFragment.java)- in the background (without UI) and the flag `setRetainInstance` in true.
 - The `StateFragment` holds the reference to the [`BasePresenter`] (https://github.com/perettijuan/moviespreview/blob/master/MoviesPreview/app/src/main/java/com/jpp/moviespreview/core/mvp/BasePresenter.java)
 - When the Activity is rotated, the `StateFragment` is not destroyed. There is no need to save state in the `BasePresenter`.

 - The implementation can be followed from [`BasePresenterActivity`](https://github.com/perettijuan/moviespreview/blob/master/MoviesPreview/app/src/main/java/com/jpp/moviespreview/core/mvp/BasePresenterActivity.java)

### Use RxAndroid for endless scrolling
And if we use RxAndroid with [`RxBinding`](https://github.com/JakeWharton/RxBinding) to detect the scroll in a RecyclerView
and request values to the API when a treshold is reached? It's seems to work OK!

## Libraries and concepts

- Support libraries
- CoordinatorLayout
- RecyclerViews
- CardViews 
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Fresco] (http://frescolib.org/)
- [The Clean Architecture](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

