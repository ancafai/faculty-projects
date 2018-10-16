#pragma once
#include "User_Controller.h"

class User_UI {
private:
	User_Ctrl &ctrl;

public:
	User_UI(User_Ctrl &ctrl_user) : ctrl(ctrl_user) {}
	virtual ~User_UI() {};
	///-------------------------------------------------------------------------------------------------
	/// <summary>	Executes the user operations. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void run_user();

private:
	///-------------------------------------------------------------------------------------------------
	/// <summary>	Shows the user menu. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	static void show_user_menu();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets all movies by genre to watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void Movie_Menu() const;

	void get_all_movies_by_genre_to_watchlist_ui() const;

	void manage_watchlist() const;
	///-------------------------------------------------------------------------------------------------
	/// <summary>	Deletes the movie from user watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void delete_movie_from_user_watchlist_ui();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Shows all movies from user watchlist. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void show_all_movies_from_user_watchlist_ui() const;

	void saveWatchlistToFile();
};