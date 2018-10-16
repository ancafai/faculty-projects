#pragma once
#include "Movie.h"
#include <vector>
class Watchlist {
protected:
	std::vector<Movie> Watchlist_Movies;
	//int current = 0;
public:
	Watchlist() {};
	virtual ~Watchlist() {};
	std::vector<Movie> get_movies() const { return Watchlist_Movies; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Adds a movie to the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="movie">	The movie. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void add_watchlist_movie(const Movie &movie);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Deletes a movie from the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	/// <param name="genre">	The genre. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void delete_watchlist_movie(const std::string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Searches for the movie with the inputed title and genre. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	/// <param name="genre">	The genre. </param>
	///
	/// <returns>	The found title and genre in watchlist. </returns>
	///-------------------------------------------------------------------------------------------------

	int find_by_title_in_watchlist(const std::string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets current movie from the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	The current movie. </returns>
	///-------------------------------------------------------------------------------------------------



	//Movie get_current_movie();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Plays the trailer of the current movie. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	//void play();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Checks if the watchlist is empty. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	true if empty, false if not. </returns>
	///-------------------------------------------------------------------------------------------------

	//bool is_empty();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets the length of the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	The length of the watchlist. </returns>
	///-------------------------------------------------------------------------------------------------

	//int get_length_watchlist();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Go to the next item in the watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	//void next();
};