#include "Watchlist.h"
#include <string>
#include <fstream>
#include "Utils.h"

using namespace std;


void Watchlist::add_watchlist_movie(const Movie &movie) {
	int result = find_by_title_in_watchlist(movie.get_title());
	if (result == -1) {
		this->Watchlist_Movies.push_back(movie);
		return;
		//return true;
	}
	throw "Movie already in the watchlist.";
	//return false;
}

void Watchlist::delete_watchlist_movie(const std::string &title) {
	int result = find_by_title_in_watchlist(title);
	if (result != -1) {
		this->Watchlist_Movies.erase(Watchlist_Movies.begin() + result);
		return;
		//return true;
	}
	throw "Movie not in the watchlist.";
	//return false;
}

int Watchlist::find_by_title_in_watchlist(const std::string &title) {
	int result = -1;
	for (int i = 0; i < this->Watchlist_Movies.size(); i++)
		if (this->Watchlist_Movies[i].get_title() == title)
			return i;
	return result;
}

/*Movie Watchlist::get_current_movie() {
	if (this->current == this->Watchlist_Movies.get_size())
		this->current = 0;
	Movie *elems = this->Watchlist_Movies.get_all_elems();
	if (elems != NULL)
		return elems[this->current];
	return Movie{};
}

void Watchlist::play() {
	if (this->Watchlist_Movies.get_size() == 0)
		return;
	Movie current_movie = this->get_current_movie();
	current_movie.play();
}

void Watchlist::next() {
	if (this->Watchlist_Movies.get_size() == 0)
		return;
	this->current++;
	Movie current_movie = this->get_current_movie();
}

bool Watchlist::is_empty() {
	return this->Watchlist_Movies.get_size() == 0;
}

int Watchlist::get_length_watchlist() {
	return this->Watchlist_Movies.get_size();
}*/