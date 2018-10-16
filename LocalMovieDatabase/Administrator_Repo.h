#pragma once
#include "Movie.h"
//#include "Dynamic_Vector.h"
#include <vector>
class Admin_Repo {
private:
	std::vector<Movie> Movies;
	std::string filename;

public:

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Constructor. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="filename">	Filename of the file. </param>
	///-------------------------------------------------------------------------------------------------

	Admin_Repo(const std::string &filename);
	//Admin_Repo();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Adds a movie to the Repository. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="m">	The Movie to process. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void add_admin_repo_movie(const Movie &m);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Removes a movie from the Repository. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	/// <param name="genre">	The genre. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void delete_admin_repo_movie(const std::string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Updates the movie from the repository with the new data. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="m">	The Movie that substitutes the old one. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void update_admin_repo_movie(const Movie &m);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Searches for the movie with the inputed title and genre in the repository. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	/// <param name="genre">	The genre. </param>
	///
	/// <returns>	The position of that movie contained in the repository. </returns>
	///-------------------------------------------------------------------------------------------------

	int find_by_title_in_repo(const std::string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets movies from database. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	The admin repo movies. </returns>
	///-------------------------------------------------------------------------------------------------

	std::vector<Movie> get_admin_repo_movies() const { return Movies; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets size of repository. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	The size admin repo. </returns>
	///-------------------------------------------------------------------------------------------------

	int get_size_admin_repo() const { return Movies.size(); }

	~Admin_Repo(){}

private:

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Reads from file. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void read_from_file();

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Writes to file. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///-------------------------------------------------------------------------------------------------

	void write_to_file();
};