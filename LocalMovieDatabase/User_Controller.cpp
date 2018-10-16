#include "User_Controller.h"
#include <string>
#include <iostream>
using namespace std;
void User_Ctrl::add_movie_to_watchlist(Movie &movie) {
	this->watchlist->add_watchlist_movie(movie);
}

void User_Ctrl::delete_movie_from_watchlist(const string &title) {
	this->watchlist->delete_watchlist_movie(title);
}

void User_Ctrl::get_genre_watchlist_to_iterate(string genre) {
	std::vector<Movie> gen_vector = this->repository.get_admin_repo_movies();

	this->genre_movies.clear();
	
	if (genre == "") {
		this->genre_movies = gen_vector;
		return;
	}
	for (auto Movie: this->repository.get_admin_repo_movies()) {
			if (Movie.get_genre() == genre && this->watchlist->find_by_title_in_watchlist(Movie.get_title()) == -1)
				this->genre_movies.push_back(Movie);
	}
}

int User_Ctrl::rate_movie(int new_nr, string &title) {
	int pos = this->repository.find_by_title_in_repo(title);
	Movie movie = this->repository.get_admin_repo_movies()[pos];
	movie.set_nr_likes(movie.get_nr_likes() + new_nr);
	this->repository.update_admin_repo_movie(movie);
	return this->repository.get_admin_repo_movies()[pos].get_nr_likes();
}

void User_Ctrl::saveWatchlist(const std::string& filename)
{
	this->watchlist->setFilename(filename);
	this->watchlist->writeToFile();
}

void User_Ctrl::openWatchlist() const
{
	this->watchlist->displayWatchlist();
}

/*void User_Ctrl::start_watchlist()
{
	this->watchlist.play();
}*/

void User_Ctrl::next_movie_watchlist()
{
	current = (current + 1) % this->genre_movies.size();
}