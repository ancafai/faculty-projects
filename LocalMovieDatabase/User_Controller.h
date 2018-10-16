#pragma once
#include "Administrator_Repo.h"
#include "Administrator_Controller.h"

#include "FileWatchlist.h"
using namespace std;

class User_Ctrl {

public:

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Constructor. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="watchlist">	The watchlist. </param>
	///-------------------------------------------------------------------------------------------------

	User_Ctrl(Admin_Repo &repo_user, FileWatchlist *file) : repository{ repo_user }, watchlist{ file } {};

	~User_Ctrl() {};
	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 06.04.2016. </remarks>
	///
	/// <returns>	The watchlist. </returns>
	///-------------------------------------------------------------------------------------------------

	std::vector<Movie> get_watchlist() const { return watchlist->get_movies(); }

	std::vector<Movie> get_genre_movies() const { return genre_movies; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Deletes the movie from watchlist described by the title. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void add_movie_to_watchlist(Movie &movie);

	void delete_movie_from_watchlist(const string &title);

	void get_genre_watchlist_to_iterate(string genre);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets a watchlist of movies containing a certain genre. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="genre">	The genre. </param>
	///
	/// <returns>	The genre watchlist. </returns>
	///-------------------------------------------------------------------------------------------------

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Rates a movie. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="movie">	[in,out] The movie. </param>
	///
	/// <returns>	An int. </returns>
	///-------------------------------------------------------------------------------------------------

	int rate_movie(int new_nr, string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Starts a watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	//void start_watchlist();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Next movie watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void next_movie_watchlist();

	Movie get_current_movie_ctrl() const { return this->genre_movies[current]; }

	/*
	Saves the watchlist.
	Throws: FileException - if the given file cannot be opened.
	*/
	void saveWatchlist(const std::string &filename);

	/*
	Opens the Watchlist, with an appropriate application.
	Throws: FileException - if the given file cannot be opened.
	*/
	void openWatchlist() const;

private:
	FileWatchlist* watchlist;
	Admin_Repo &repository;
	std::vector<Movie> genre_movies;
	int current = 0;

};