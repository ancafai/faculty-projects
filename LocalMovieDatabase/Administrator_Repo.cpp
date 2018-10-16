#include "Administrator_Repo.h"
#include "Movie_Validator.h"
#include <string>
#include <fstream>
#include "Utils.h"
#include <algorithm>
using namespace std;

Admin_Repo::Admin_Repo(const std::string &filename){
	this->filename = filename;
	this->read_from_file();
}
/*Admin_Repo::Admin_Repo() {

}*/

void Admin_Repo::add_admin_repo_movie(const Movie &movie) {
	int result = find_by_title_in_repo(movie.get_title());
	if (result == -1) {
		//this->Movies = this->Movies + movie;
		this->Movies.push_back(movie);
		this->write_to_file();
		return;
		//return true;
	}
	throw "Movie already in the list";
	//return false;

}

void Admin_Repo::delete_admin_repo_movie(const string &title) {
	int result = find_by_title_in_repo(title);
	if (result != -1) {
		//this->Movies = this->Movies - this->Movies[result];
		this->Movies.erase(this->Movies.begin() + result);
		this->write_to_file();
		return;
		//return true;
	}
	throw "Movie is not in the list.";
	//return false;
}

void Admin_Repo::update_admin_repo_movie(const Movie &movie) {
	int result = find_by_title_in_repo(movie.get_title());
	Movie new_movie;
	if (result != -1) {
		new_movie = Movies[result];
		new_movie.set_nr_likes(movie.get_nr_likes());
		new_movie.set_year(movie.get_year());
		new_movie.set_url(movie.get_url());
		Movies.erase(this->Movies.begin() + result);
		Movies.emplace(this->Movies.begin() + result, new_movie);
		
		this->write_to_file();
		return;
		//return true;
	}
	throw "Movie not in the list.";
	//return false;
}

int Admin_Repo::find_by_title_in_repo(const string &title) {
	/*
	Without auto and vector

	for (int i = 0; i < this->get_size_admin_repo(); i++)
	if (this->Movies[i].get_title() == title)
	return i;

	return res;
	*/
	/*
	Without auto with vector

	for (int i = 0; i < this->Movies.size(); i++)
	if (this->Movies[i].get_title() == title)
	return i;
	*/

	
	//returns an iterator

	auto i = find_if(this->Movies.begin(), this->Movies.end(), [title](Movie &m) {return (m.get_title() == title); });
	if (i == this->Movies.end())
	return -1;
	return i - this->Movies.begin();
	
}

void Admin_Repo::read_from_file() {
	ifstream file(this->filename);
	string line;
	while (getline(file, line)) {
		//tokenize the line to take data corresponding to a song
		string tokens[20];
		int length = 0, year, nr_likes;
		tokenize(line, '|', tokens, length);
		year = atoi(tokens[2].c_str());
		nr_likes = atoi(tokens[3].c_str());
		if (length != 5)
			continue;

		Movie movie(tokens[0], tokens[1], year, nr_likes, tokens[4]);
		this->Movies.push_back(movie);
	}
}

void Admin_Repo::write_to_file() {
	ofstream file(this->filename);
	for (auto Movie : this->Movies) {

		file << Movie.get_title() << " | " << Movie.get_genre() << " | " << Movie.get_year() << " | " << Movie.get_nr_likes() << " | " << Movie.get_url() << endl;
	}

	file.close();

}