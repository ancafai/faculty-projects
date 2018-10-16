#include "Administrator_Controller.h"

void Admin_Ctrl::add_movie_to_admin_repo(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string url) {
	Movie movie{ title,genre,year,nr_likes,url };
	this->repo.add_admin_repo_movie(movie);
}

void Admin_Ctrl::delete_movie_to_admin_repo(const std::string &title) {
	this->repo.delete_admin_repo_movie(title);
}

void Admin_Ctrl::update_movie_to_admin_repo(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string url) {
	Movie movie(title, genre, year, nr_likes, url);
	this->repo.update_admin_repo_movie(movie);
}