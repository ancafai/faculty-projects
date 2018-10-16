#pragma once
#include "Administrator_Repo.h"

class Admin_Ctrl {
private:
	Admin_Repo &repo;

public:

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Constructor. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="repo">	The repo. </param>
	///-------------------------------------------------------------------------------------------------

	Admin_Ctrl(Admin_Repo &repo) : repo(repo) {}

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Gets the repo. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <returns>	The repo. </returns>
	///-------------------------------------------------------------------------------------------------

	Admin_Repo get_admin_repo() const { return repo; }

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Adds a movie to Admin_Repo. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">   	The title. </param>
	/// <param name="genre">   	The genre. </param>
	/// <param name="year">	   	The year. </param>
	/// <param name="nr_likes">	The nr likes. </param>
	/// <param name="url">	   	URL of the document. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void add_movie_to_admin_repo(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string url);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Deletes the movie to Admin_Repo. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">	The title. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void delete_movie_to_admin_repo(const std::string &title);

	///-------------------------------------------------------------------------------------------------
	/// <summary>	Updates the movie to Admin_Repo. </summary>
	///
	/// <remarks>	Faiciuc Anca, 05.04.2016. </remarks>
	///
	/// <param name="title">   	The title. </param>
	/// <param name="genre">   	The genre. </param>
	/// <param name="year">	   	The year. </param>
	/// <param name="nr_likes">	The nr likes. </param>
	/// <param name="url">	   	URL of the document. </param>
	///
	/// <returns>	true if it succeeds, false if it fails. </returns>
	///-------------------------------------------------------------------------------------------------

	void update_movie_to_admin_repo(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string url);

	~Admin_Ctrl(){}
};