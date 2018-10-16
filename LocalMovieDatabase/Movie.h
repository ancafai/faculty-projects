#pragma once
#include <string>

class Movie {

private:
	std::string title;
	std::string genre;
	int year;
	int nr_likes;
	std::string url;	//YouTube trailer link

public:
	// default constructor for a movie
	Movie();

	// constructor with parameters
	Movie(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string &url);

	bool operator==(Movie movie);

	// Getters
	std::string get_title() const { return title; }
	std::string get_genre() const { return genre; }
	int get_year() const { return year; }
	int get_nr_likes() const { return nr_likes; }
	std::string get_url() const { return url; }

	//Input: the new release year.
	void set_year(int new_year) { year = new_year; }

	//Setter for a movies number of likes.
	//Input: the new number of likes.
	void set_nr_likes(int new_nr_likes) { nr_likes = new_nr_likes; }

	//Setter for a movies trailer.
	//Input: the new trailer link.
	void set_url(std::string &new_url) { url = new_url; }

	// Play the trailer: the page corresponding to the source link is opened in a browser
	void play();

private:
	friend std::ostream& operator<< (std::ostream& os, const Movie &movie)
	{
		os << movie.title << " | " << movie.genre << " | " << movie.year << " | " << movie.nr_likes << " | " << movie.url << std::endl;

		return os;
	}
};

void test_movie();