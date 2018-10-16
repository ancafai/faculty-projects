#pragma once
#include "Administrator_Controller.h"

class Admin_UI {
private:
	Admin_Ctrl &ctrl;

public:
	Admin_UI(Admin_Ctrl &ctrl) : ctrl(ctrl) {};
	~Admin_UI() {};
	///-------------------------------------------------------------------------------------------------
	/// <summary>	Executes the admin operations. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void run_admin();

private:

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Shows the admin menu. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	static void show_admin_menu();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Adds movie to admin database. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void add_movie_to_admin_repository_ui() const;

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Deletes the movie from admin database. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void delete_movie_from_admin_repository_ui() const;

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Updates the movie from admin database. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void update_movie_from_admin_repository_ui() const;

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Shows all movies from admin database. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void show_all_movies_from_admin_repository_ui() const;

};